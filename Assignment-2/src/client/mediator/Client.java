package mediator;

import com.google.gson.Gson;
import model.Message;
import model.Model;
import utility.UnnamedPropertyChangeSubject;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Client implements Model, UnnamedPropertyChangeSubject {

    private Model model;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;
    private boolean waiting;
    private ArrayList<String> reply;
    private PropertyChangeSupport property;

    public Client(Model model, String host, int port) throws IOException {
        this.model = model;
        socket = new Socket(host, port);
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
        gson = new Gson();
        waiting = false;
        reply = null;
        property = new PropertyChangeSupport(this);
        Thread clientReceiver = new Thread(new ClientReceiver(this, in));
        clientReceiver.setDaemon(true);
        clientReceiver.start();
    }

    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public synchronized void receive(String s) {
        ArrayList<Object> temp = gson.fromJson(s, ArrayList.class);
        switch (temp.get(0).toString()) {
            case "message" : {
                property.firePropertyChange("message", temp.get(1), gson.fromJson(temp.get(2).toString(), Message.class));
                break;
            }
            case "requestData" : {
                ArrayList<Message> list = gson.fromJson(temp.get(2).toString(), ArrayList.class);
                System.out.println(list.get(0));
                for (Message message : list) {
                    property.firePropertyChange("message", temp.get(1), message);
                }
                break;
            }
        }
        ArrayList<String> rxPackage = gson.fromJson(s, ArrayList.class);
        if (waiting) {
            reply = rxPackage;
            notify();
        }
    }

    private synchronized ArrayList<String> waitingForReply() {
        waiting = true;
        while (reply == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        waiting = false;
        ArrayList<String> toReturn = reply;
        reply = null;
        return toReturn;
    }

    @Override
    public void addListenerOfBroadcasts(PropertyChangeListener listener) {
        // Temp.
    }

    @Override
    public void register(String username, String password) throws Exception {
        if (username == null || username.isEmpty()) throw new Exception("Type in a username.");
        if (password == null || password.isEmpty()) throw new Exception("Type in a password.");
        out.println(gson.toJson(new ArrayList<>(Arrays.asList("register", username, password))));
        ArrayList<String> list = waitingForReply();
        if (list.get(0).equals("error")) throw new Exception(list.get(1));
    }

    @Override
    public void login(String username, String password) throws Exception {
        if (username == null || username.isEmpty()) throw new Exception("Type in a username.");
        if (password == null || password.isEmpty()) throw new Exception("Type in a password.");
        out.println(gson.toJson(new ArrayList<>(Arrays.asList("login", username, password))));
        ArrayList<String> list = waitingForReply();
        if (list.get(0).equals("error")) throw new Exception(list.get(1));
    }

    @Override
    public void createChat(String chatName) throws Exception {
        if (chatName == null || chatName.isEmpty()) throw new Exception("Type in a name of the chat to be created.");
        out.println(gson.toJson(new ArrayList<>(Arrays.asList("createChat", chatName))));
    }

    @Override
    public void joinChat(String uuid) throws Exception {
        if (uuid == null || uuid.isEmpty()) throw new Exception("Type in the uuid of the chat to join.");
        out.println(gson.toJson(new ArrayList<>(Arrays.asList("join", uuid))));
        ArrayList<String> list = waitingForReply();
        if (list.get(0).equals("error")) throw new Exception(list.get(1));
    }

    @Override
    public void sendMessage(String uuid, String message) throws Exception {
        if (uuid == null || uuid.isEmpty()) throw new Exception("Select a chat for the message to be sent.");
        if (message == null || message.isEmpty()) throw new Exception("Type in a message to be sent.");
        out.println(gson.toJson(new ArrayList<>(Arrays.asList("message", uuid, message))));
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
