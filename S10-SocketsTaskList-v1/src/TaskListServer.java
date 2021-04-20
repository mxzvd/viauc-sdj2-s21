import java.io.IOException;
import java.net.ServerSocket;

public class TaskListServer {

    private TaskList list;
    private ServerSocket welcomeSocket;

    TaskListServer(TaskList taskList, int port) throws IOException {
        list = taskList;
        welcomeSocket = new ServerSocket(port);
        execute();
    }

    private void execute() throws IOException {
        while (true) {
            Thread communicationServer = new Thread(new TaskListCommunicationThreadHandler(welcomeSocket.accept(), list));
            communicationServer.start();
        }
    }
}
