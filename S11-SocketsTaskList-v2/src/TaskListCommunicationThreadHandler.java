import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TaskListCommunicationThreadHandler implements Runnable {

    private TaskList list;
    private DataInputStream in;
    private DataOutputStream out;
    private String ip;
    private Gson gson;

    TaskListCommunicationThreadHandler(Socket socket, TaskList tasks) throws IOException {
        list = tasks;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        ip = socket.getInetAddress().getHostAddress();
        gson = new Gson();
    }


    @Override public void run() {
        while (true) {
            try {
                switch (in.readUTF()) {
                    case "SIZE": {
                        out.writeInt(list.size());
                        break;
                    }
                    case "GET": {
                        Task task = list.getAndRemoveNextTask();
                        if (task != null) {
                            out.writeUTF(gson.toJson(task));
                        } else {
                            out.writeUTF("ERROR");
                        }
                        break;
                    }
                    case "ADD": {
                        list.add(gson.fromJson(in.readUTF(), Task.class));
                        out.writeUTF("ADD");
                        break;
                    }
                    default: {
                        out.writeUTF("EXIT");
                        return;
                    }
                }
            } catch (IOException e) {
                // Do nothing.
            }
        }
    }
}
