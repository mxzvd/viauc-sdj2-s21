package mediator;

import java.io.*;
import java.net.*;
import java.util.Set;
import model.Model;

public class UppercaseConnector implements Runnable {

    private final int PORT = 6789;
    private Model model;
    private boolean running;
    private ServerSocket welcomeSocket;

    public UppercaseConnector(Model model) {
        this.model = model;
    }

    @Override
    public void run() {
        try {
            model.addLog("Starting Server...");
            welcomeSocket = new ServerSocket(PORT);

            running = true;
            while (running) {
                model.addLog("Waiting for a client...");
                Socket socket = welcomeSocket.accept();
                UppercaseClientHandler clientHandler = new UppercaseClientHandler(socket,
                        model);
                Thread clientThread = new Thread(clientHandler);
                clientThread.setDaemon(true);
                clientThread.start();
            }
        } catch (IOException e) {
            model.addLog("Error: " + e.getMessage());
        }
    }

    public void close() {
        running = false;
        try {
            welcomeSocket.close();
        } catch (Exception e) {
            // Do nothing.
        }
    }
}
