import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {

    private ServerSocket welcomeSocket;

    public ChatServer(int port) throws IOException {
        welcomeSocket = new ServerSocket(port);
    }

    public void execute() throws IOException {
        while (true) new Thread(new CommunicationThreadHandler(welcomeSocket.accept())).start();
    }
}
