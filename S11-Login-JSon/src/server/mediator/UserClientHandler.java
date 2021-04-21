package server.mediator;

import com.google.gson.Gson;
import server.model.Model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserClientHandler implements Runnable {

    private Model model;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Gson gson;
    private String clientIp;

    public UserClientHandler(Socket socket, Model model) throws IOException {
        this.model = model;
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
        running = false;
        gson = new Gson();
        clientIp = socket.getInetAddress().getHostAddress();
    }

    public void close() throws IOException {
        if (running) {
            running = false;
            socket.close();
        }
    }

    @Override public void run() {
        running = true;
        while (running) {
            try {
                UserPackage userPackage = gson.fromJson(in.readLine(), UserPackage.class);
                model.addUser(userPackage.getUser(), userPackage.getPassword());
                out.println("Success: you are now logged in");
                break;
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        }
    }
}
