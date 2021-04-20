import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        TaskListServer server = new TaskListServer(new TaskList(), 5678);
    }
}
