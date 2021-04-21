package client.mediator;

import client.model.Model;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserClient implements Model {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;

    public UserClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())) {
            @Override public String readLine() throws IOException {
                String readData = super.readLine();
                System.out.println("> Fr: [\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] > " + readData);
                return readData;
            }
        };
        out = new PrintWriter(socket.getOutputStream(), true){
            @Override public void println(String x) {
                super.println(x);
                System.out.println("< To: [\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] < " + x);
            }
        };
        gson = new Gson();
    }

    public void disconnect() throws IOException {
        socket.close();
    }

    @Override public void login(String userName, String password) throws Exception {
        out.println(gson.toJson(new User(userName, password)));
        try {
            String receivedData = in.readLine();
            if (!receivedData.substring(0, receivedData.indexOf(" ")).equals("Success:")) {
                throw new Exception(receivedData);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
