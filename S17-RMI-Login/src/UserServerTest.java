import common.RemoteLogin;
import server.mediator.UserServer;
import server.model.ModelManager;
import java.io.IOException;

public class UserServerTest {
    public static void main(String[] args) throws IOException {
        RemoteLogin server = new UserServer(new ModelManager());
    }
}
