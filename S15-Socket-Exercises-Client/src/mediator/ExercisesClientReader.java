package mediator;

import java.io.BufferedReader;
import java.io.IOException;

public class ExercisesClientReader implements Runnable {

    private ExercisesClient client;
    private BufferedReader in;
    private boolean running;

    public ExercisesClientReader(ExercisesClient client, BufferedReader in) {
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
