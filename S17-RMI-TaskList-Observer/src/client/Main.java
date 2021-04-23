package client;

public class Main {
    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {
            host = args[0];
        }
        Client client = null;
        try {
            client = new Client(host);
        } catch (Exception e) {
            System.err.println("Could not connect to the server.");
            System.err.println(e.getMessage());
        }
        client.start();
    }
}
