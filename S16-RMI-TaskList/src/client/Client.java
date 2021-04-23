package client;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        if (args.length > 0) {
            host = args[0];
        }
        RmiTaskClient client = new RmiTaskClient(host);
        client.start();
    }
}
