package mediator;

import model.Model;
import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {

    private final int PORT = 2989;
    private Model model;
    private ServerSocket welcomeSocket;
    private boolean running;

    public Server(Model model) throws IOException {
        this.model = model;
        welcomeSocket = new ServerSocket(PORT);
        running = false;
    }

    public void close() throws IOException {
        running = false;
        welcomeSocket.close();
        System.out.println("\u001b[35mServer closed.\u001b[39m");
    }

    @Override
    public void run() {
        running = true;
        System.out.println("\u001b[35mWaiting for clients.\u001b[39m");
        while (running) {
            try {
                Thread clientHandler = new Thread(new ClientHandler(model, welcomeSocket.accept()));
                clientHandler.setDaemon(true);
                clientHandler.start();
                System.out.println("\u001b[35mNew client connected.\u001b[39m");
            } catch (IOException e) {
                System.out.println("\u001b[35mServer couldn't dispatch a handler for incoming client.\u001b[39m");
                System.err.println(e.getMessage());
            }
        }
    }
}
