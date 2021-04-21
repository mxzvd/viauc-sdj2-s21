package mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UppercaseClient implements ServerModel {

    public static final String HOST = "localhost";
    public static final int PORT = 6789;

    private String host;
    private int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public UppercaseClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public UppercaseClient() {
        this.host = HOST;
        this.port = PORT;

    }

    @Override public void connect() throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())) {
            @Override public String readLine() throws IOException {
                String rxData = super.readLine();
                System.out.println("> \u001b[36mRX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] > " + rxData);
                return rxData;
            }
        };
        out = new PrintWriter(socket.getOutputStream(), true) {
            @Override public void println(String txData) {
                super.println(txData);
                System.out.println("< \u001b[36mTX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] < " + txData);
            }
        };
    }

    @Override public void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    @Override public String convert(String source) throws IOException {
        out.println(source);
        return in.readLine();
    }
}
