package server;

public class Server {
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        RemoteTaskList server = new RmiTaskServer();
    }
}
