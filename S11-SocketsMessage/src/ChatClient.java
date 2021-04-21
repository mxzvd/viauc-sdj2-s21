import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private Scanner input;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

    public ChatClient(String host, int port) throws IOException {
        input = new Scanner(System.in);
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())) {
            @Override public String readLine() throws IOException {
                String rxData = super.readLine();
                System.out.println("> \u001b[36mRX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + host + "\u001b[39m] > " + rxData);
                return rxData;
            }
        };
        out = new PrintWriter(socket.getOutputStream(), true){
            @Override public void println(String x) {
                super.println(x);
                System.out.println("< \u001b[36mTX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + host + "\u001b[39m] < " + x);
            }
        };
    }

    public void execute() {
        Gson gson = new Gson();
        String nextLine;
        while (true) {
            System.out.print("Type a message: ");
            nextLine = input.nextLine();
            if (nextLine != null && nextLine.equals("EXIT")) {
                out.println("EXIT");
                break;
            }
            out.println(gson.toJson(new Message(nextLine)));
        }
    }

    public void close() throws IOException {
        socket.close();
        input.close();
    }
}
