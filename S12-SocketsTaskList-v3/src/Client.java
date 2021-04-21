import client.TaskListClient;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        new TaskListClient("localhost", 5678);
    }
}
