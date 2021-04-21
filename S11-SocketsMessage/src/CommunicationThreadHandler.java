import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicationThreadHandler implements Runnable {

    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private String ip;

    public CommunicationThreadHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())) {
            @Override public String readLine() throws IOException {
                String rxData = super.readLine();
                System.out.println("> \u001b[36mRX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] > " + rxData);
                return rxData;
            }
        };
        out = new PrintWriter(socket.getOutputStream(), true){
            @Override public void println(String x) {
                super.println(x);
                System.out.println("< \u001b[36mTX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] < " + x);
            }
        };
        ip = socket.getInetAddress().getHostAddress();
    }

    @Override public void run() {
        Gson gson = new Gson();
        while (true) {
            try {
                String rxData = in.readLine();
                if (rxData.equals("EXIT")) break;
                System.out.println(gson.fromJson(rxData, Message.class));
            } catch (IOException e) {
                break;
            }
        }
    }
}
