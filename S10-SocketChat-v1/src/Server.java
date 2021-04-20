import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static void logIncoming(String client, String message) {
        System.out.println("> Fr: [\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + client + "\u001b[39m] > " + message);
    }

    private static void logOutgoing(String client, String message) {
        System.out.println("< To: [\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + client + "\u001b[39m] < " + message);
    }

    private static ArrayList<String> log = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        final int PORT = 5678;
        ServerSocket welcomeSocket = new ServerSocket(PORT);

        while (true) {
            Socket socket = welcomeSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String client = socket.getInetAddress().getHostAddress();

            String request = in.readLine();
            logIncoming(client, request);

            if (request.equals("connect")) {
                out.println("Connected");
                logOutgoing(client, "Connected");

                do {
                    request = in.readLine();
                    logIncoming(client, request);
                    log.add(request);
                } while (!request.equals("exit"));
            }
            socket.close();
            logOutgoing(client, "Disconnected");
        }
    }
}
