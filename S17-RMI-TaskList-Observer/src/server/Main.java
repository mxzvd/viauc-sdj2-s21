package server;

public class Main {
    public static void main(String[] args) {
        try {
            RemoteInterface server = new Server();
        } catch (Exception e) {
            System.err.println("Could not start the server.");
            System.err.println(e.getMessage());
        }
    }
}
