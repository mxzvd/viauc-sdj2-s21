package mediator;

import model.Model;
import java.io.IOException;
import java.net.ServerSocket;

public class ExercisesServer implements Runnable {

    private final int PORT = 2910;
    private boolean running;
    private ServerSocket welcomeSocket;
    private Model model;

    public ExercisesServer(Model model) throws IOException {
        running = false;
        welcomeSocket = new ServerSocket(PORT);
        this.model = model;
    }

    public void close() {
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
        while (running) {
            try {
                Thread clientHandlerThread = new Thread(new ExercisesClientHandler(welcomeSocket.accept(), model));
                clientHandlerThread.setDaemon(true);
                clientHandlerThread.start();
                System.out.println("\u001b[35mClient connected.\u001b[39m");
            } catch (IOException e) {
                // Do nothing.
            }
        }
    }
}
