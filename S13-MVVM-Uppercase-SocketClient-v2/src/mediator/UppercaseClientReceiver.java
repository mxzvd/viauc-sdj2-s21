package mediator;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;

public class UppercaseClientReceiver implements Runnable {

    private UppercaseClient client;
    private BufferedReader in;
    private Gson gson;

    public UppercaseClientReceiver(UppercaseClient client, BufferedReader in) {
        this.client = client;
        this.in = in;
        gson = new Gson();
    }

    @Override public void run() {
        while (true) {
            try {
                Message message = gson.fromJson(in.readLine(), Message.class);
                if (message == null) continue;
                switch (message.getType()) {
                    case "reply" : {
                        client.converted(message.getMessage());
                        break;
                    }
                    case "broadcast" : {
                        client.setMessage(message.getMessage());
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
