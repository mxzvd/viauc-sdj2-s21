package mediator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.*;
import com.google.gson.Gson;
import model.Model;

public class UppercaseClientHandler implements Runnable, PropertyChangeListener {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Model model;
    private boolean running;
    private Gson gson;

    public UppercaseClientHandler(Socket socket, Model model) throws IOException {
        this.model = model;
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        gson = new Gson();
        model.addListener(this);
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                String request = gson.fromJson(in.readLine(), Message.class).getMessage();
                model.addLog("Client> " + request);
                String reply = model.convert(request);
                model.addLog("Server> " + reply);
                out.println(gson.toJson(new Message("reply", reply)));
                if (request.contentEquals("quit")) {
                    close();
                }
            } catch (Exception e) {
                model.addLog("Client error");
                close();
            }
        }
        close();
    }

    public void close() {
        running = false;
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            // Do nothing.
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("broadcast")) out.println(gson.toJson(new Message("broadcast", evt.getNewValue().toString())));
    }
}
