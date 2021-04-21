import server.mediator.UserServer;
import server.model.ModelManager;
import java.io.IOException;

public class UserServerTest {
    public static void main(String[] args) throws IOException {
        Thread serverThread = new Thread(new UserServer(new ModelManager()));
        serverThread.start();
    }
}
