package server;

import com.google.gson.Gson;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// On the large scale, for each connected client the server creates an instance of this class (each instance operates parallel on different threads).
// Every instance of this class is meant to serve/handle communication with only one specific client.
// A simple analogy could be: Client is a person at a restaurant table, while each instance of this class is a waiter meant to serve/handle only that specific table with the people there.
public class TaskListCommunicationThreadHandler implements Runnable, PropertyChangeListener {

    private TaskList list;
    private BufferedReader in;
    private PrintWriter out;
    private String ip;
    private Gson gson;

    TaskListCommunicationThreadHandler(Socket socket, TaskList tasks) throws IOException {
        list = tasks;
        ip = socket.getInetAddress().getHostAddress();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())) {
            @Override public String readLine() throws IOException {
                String rxData = super.readLine();
                System.out.println("> \u001b[36mRX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + ip + "\u001b[39m] > " + rxData);
                return rxData;
            }
        };
        out = new PrintWriter(socket.getOutputStream(), true) {
            @Override public void println(String txData) {
                super.println(txData);
                System.out.println("< \u001b[36mTX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + ip + "\u001b[39m] < " + txData);
            }
        };
        gson = new Gson();
        tasks.addListener(this);
    }

    // Code responsible for request/reply communication.
    // The thread continuously runs and wait for any request from the client.
    // Depending on the request the client will be served different replies based on the switch below.
    @Override public void run() {
        while (true) {
            try {
                switch (in.readLine()) {
                    case "SIZE": {
                        out.println(list.size());
                        break;
                    }
                    case "GET": {
                        // Here if the task we get is null, that means that it won't fire the property.
                        // Otherwise we get a proper task object and the property remove is also fired to the propertyChange method declared below.
                        Task task = list.getAndRemoveNextTask();
                        out.println(task == null ? "ERROR" : gson.toJson(task));
                        break;
                    }
                    case "ADD": {
                        list.add(gson.fromJson(in.readLine(), Task.class));
                        break;
                    }
                    default: {
                        out.println("EXIT");
                        list.removeListener(this);
                        in.close();
                        out.close();
                        return;
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // Code responsible for broadcasting communication:
    // Upon creation we delegate this class to be a listener to the model of the server.
    // So whenever we will have an update on the model, the method down below will be called.
    // Because we have many, many instances of this class running in parallel each one serving one individual client, all of them will be receive the event change "simultaneously".
    // So therefore each of them will let their client know about the update, therefore in the big picture saying that the event is a "broadcast".
    @Override public void propertyChange(PropertyChangeEvent evt) {
        out.println(evt.getPropertyName());
    }
}
