package server;

public class Server {
    public static void main(String[] args) {
        RemoteTaskList server = new RmiTaskServer();
    }
}
