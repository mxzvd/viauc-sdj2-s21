package server.mediator;

import server.model.Model;
import java.io.IOException;
import java.net.ServerSocket;

public class UserServer implements Runnable {

    private final static int PORT = 2910;

    private Model model;
    private boolean running;
    private ServerSocket welcomeSocket;

    public UserServer(Model model) throws IOException {
        this.model = model;
        running = false;
        welcomeSocket = new ServerSocket(PORT);
    }

    public void close() {
        if (running) {
            running = false;
            Thread.currentThread().interrupt();
        }
    }

    @Override public void run() {
        running = true;
        while (running) {
            try {
                UserClientHandler client = new UserClientHandler(welcomeSocket.accept(), model);
                Thread clientHandlerThread = new Thread(client);
                clientHandlerThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
