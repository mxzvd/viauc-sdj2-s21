import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        TaskListClient client = new TaskListClient("localhost", 5678);
    }
}
