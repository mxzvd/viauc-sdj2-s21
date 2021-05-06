package mediator;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReceiver implements Runnable {

    private Client client;
    private BufferedReader in;
    private boolean running;

    public ClientReceiver(Client client, BufferedReader in) {
        this.client = client;
        this.in = in;
    }

    public void close() {
        running = false;
    }

    @Override public void run() {
        running = true;
        while (running) {
            try {
                client.receive(in.readLine());
            } catch (IOException e) {
                // Do nothing.
            }
        }
    }
}
