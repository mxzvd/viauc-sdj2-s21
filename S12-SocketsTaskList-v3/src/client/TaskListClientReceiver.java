package client;

import java.io.BufferedReader;
import java.io.IOException;

public class TaskListClientReceiver implements Runnable {

    private TaskListClient client;
    private BufferedReader in;

    public TaskListClientReceiver(TaskListClient client, BufferedReader in) {
        this.client = client;
        this.in = in;
    }

    @Override public void run() {
        String line = "";
        do {
            try {
                line = in.readLine();
                client.receive(line);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } while (!line.equals("EXIT"));
    }
}
