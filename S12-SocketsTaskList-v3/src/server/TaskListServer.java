package server;

import java.io.IOException;
import java.net.ServerSocket;

public class TaskListServer {

    private TaskList list;
    private ServerSocket welcomeSocket;

    public TaskListServer(TaskList taskList, int port) throws IOException {
        list = taskList;
        welcomeSocket = new ServerSocket(port);
        execute();
        welcomeSocket.close();
    }

    private void execute() throws IOException {
        while (true) new Thread(new TaskListCommunicationThreadHandler(welcomeSocket.accept(), list)).start();
    }
}
