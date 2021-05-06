package mediator;

import com.google.gson.Gson;
import model.Message;
import model.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientHandler implements Runnable, PropertyChangeListener {

    private HandlerState state;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Gson gson;

    public ClientHandler(Model model, Socket socket) throws IOException {
        state = new UnauthorizedState(model);
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())) {
            @Override public String readLine() throws IOException {
                String rxData = super.readLine();
                System.out.println("> \u001b[36mRX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] > " + rxData);
                return rxData;
            }
        };
        out = new PrintWriter(socket.getOutputStream(), true) {
            @Override public void println(String txData) {
                super.println(txData);
                System.out.println("< \u001b[36mTX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] < " + txData);
            }
        };
        running = false;
        gson = new Gson();
    }

    public void setState(HandlerState state) {
        this.state = state;
    }

    public void close() {
        try {
            running = false;
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("\u001b[35mClient handler was not able close.\u001b[39m");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                ArrayList<String> list = gson.fromJson(in.readLine(), ArrayList.class);
                try {
                    switch (list.get(0)) {
                        case "register" : {
                            state.register(this, list.get(1), list.get(2));
                            out.println(gson.toJson(new ArrayList<>(Arrays.asList("register", "success"))));
                            break;
                        }
                        case "login" : {
                            state.login(this, list.get(1), list.get(2));
                            out.println(gson.toJson(new ArrayList<>(Arrays.asList("login", "success"))));
                            break;
                        }
                        case "createChat" : {
                            state.createChat(this, list.get(1));
                            break;
                        }
                        case "message" : {
                            state.messageChat(list.get(1), list.get(2), socket.getInetAddress().toString());
                            break;
                        }
                        case "join" : {
                            state.joinChat(list.get(1), this);
                            out.println(gson.toJson(new ArrayList<>(Arrays.asList("joinChat", "success"))));
                            break;
                        }
                    }
                } catch (Exception e) {
                    out.println(gson.toJson(new ArrayList<>(Arrays.asList("error", e.getMessage()))));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
                if (e.getMessage().equals("Connection reset")) close();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "message" : {
                out.println(gson.toJson(new ArrayList<>(Arrays.asList("message", evt.getOldValue().toString(), gson.toJson(evt.getNewValue())))));
                break;
            }
            case "requestData" : {
                ArrayList<Message> list = (ArrayList) evt.getNewValue();
                for (Message message : list) {
                    out.println(gson.toJson(new ArrayList<>(Arrays.asList("message", evt.getOldValue().toString(), gson.toJson(message)))));
                }
                break;
            }
        }
    }
}
