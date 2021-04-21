import server.TaskListServer;
import server.TaskList;
import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        new TaskListServer(new TaskList(), 5678);
    }
}
