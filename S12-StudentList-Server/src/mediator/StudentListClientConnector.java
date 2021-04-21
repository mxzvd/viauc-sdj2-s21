package mediator;

import model.Model;
import java.io.IOException;
import java.net.ServerSocket;

public class StudentListClientConnector implements Runnable {

    private final int PORT = 9876;
    private Model model;
    private boolean running;
    private ServerSocket welcomeSocket;

    public StudentListClientConnector(Model model) throws IOException {
        this.model = model;
        running = false;
        welcomeSocket = new ServerSocket(PORT);
    }

    public void stop() {
        try {
            running = false;
            welcomeSocket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override public void run() {
        running = true;
        System.out.println("\u001b[35mWaiting for clients.\u001b[39m");
        ThreadGroup clientHandlerGroup = new ThreadGroup("handlers");
        while (running) {
            try {
                Thread clientHandlerThread = new Thread(clientHandlerGroup, new StudentListClientHandler(welcomeSocket.accept(), model, clientHandlerGroup));
                clientHandlerThread.setDaemon(true);
                clientHandlerThread.start();
                System.out.println("\u001b[35mClient connected.\u001b[39m");
            } catch (IOException e) {
                // Do nothing.
            }
        }
    }
}
