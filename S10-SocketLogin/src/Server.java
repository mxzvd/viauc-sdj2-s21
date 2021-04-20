import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        final int PORT = 5678;
        ServerSocket welcomeSocket = new ServerSocket(PORT);

        while (true) {

            Socket socket = welcomeSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())) {
                @Override public String readLine() throws IOException {
                    String rxData = super.readLine();
                    System.out.println("> \u001b[36mRX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] > " + rxData);
                    return rxData;
                }
            };
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true) {
                @Override public void println(String txData) {
                    super.println(txData);
                    System.out.println("< \u001b[36mTX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] < " + txData);
                }
            };

            // Wait for a first command from the client.
            String request = in.readLine();

            // If the command is "connect" proceed to ask for the rest of the data, otherwise disconnect.
            if (request.equals("connect")) {
                // Send him message asking for username.
                out.println("Username?");

                // Wait for a reply for the username.
                request = in.readLine();

                // Send him message asking for password.
                out.println("Password?");

                // Wait for a reply with the password.
                request = in.readLine();

                // Send him the approve message.
                out.println("Approved");
            } else {
                // Send him disconnect message.
                out.println("Disconnected");
            }
        }
    }
}
