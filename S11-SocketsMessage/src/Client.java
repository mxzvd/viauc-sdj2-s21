public class Client {
    public static void main(String[] args) {
        try {
            new ChatClient("127.0.0.1", 2784).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
