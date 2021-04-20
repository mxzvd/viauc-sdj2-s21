import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static void logIncoming(String client, String message) {
        System.out.println("> Fr: [\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + client + "\u001b[39m] > " + message);
    }

    private static void logOutgoing(String client, String message) {
        System.out.println("< To: [\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + client + "\u001b[39m] < " + message);
    }

    public static void main(String[] args) throws IOException {

        final int PORT = 5678;
        final String HOST = "localhost";

        Scanner input = new Scanner(System.in);
        Socket socket = new Socket(HOST, PORT);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        System.out.print("Write a line for the server: ");
        String request = input.nextLine();
        out.println(request);
        logOutgoing(HOST, request);

        String reply = in.readLine();
        logIncoming(HOST, reply);
        if (reply.equals("Username?")) {

            System.out.print("Username: ");
            request = input.nextLine();
            out.println(request);
            logOutgoing(HOST, request);

            reply = in.readLine();
            logIncoming(HOST, reply);

            System.out.print("Password: ");
            request = input.nextLine();
            out.println(request);
            logOutgoing(HOST, request);

            reply = in.readLine();
            logIncoming(HOST, reply);
        }
        socket.close();
    }
}
