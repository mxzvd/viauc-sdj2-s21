package mediator;

import com.google.gson.Gson;
import model.Model;
import model.Student;
import network.NetworkPackage;
import network.NetworkType;
import network.StringPackage;
import network.StudentPackage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class StudentListClientHandler implements Runnable, PropertyChangeListener {

    private Model model;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Gson gson;

    public StudentListClientHandler(Socket socket, Model model, ThreadGroup group) throws IOException {
        this.model = model;
        this.socket = socket;
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
        running = false;
        gson = new Gson();
        model.addListener(this);
    }

    public void stop() {
        try {
            running = false;
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            // Do nothing.
        }
    }

    @Override public void run() {
        running = true;
        while (running) {
            try {
                String rxJson = in.readLine();
                NetworkPackage networkPackage = gson.fromJson(rxJson, NetworkPackage.class);
                try {
                    switch (networkPackage.getType()) {
                        case NUMBER:
                        case NAME: {
                            StringPackage stringPackage = gson.fromJson(rxJson, StringPackage.class);
                            out.println(gson.toJson(new StudentPackage(networkPackage.getType() == NetworkType.NUMBER ? model.getStudentByStudyNumber(stringPackage.getValue()) : model.getStudentByName(stringPackage.getValue()))));
                            break;
                        }
                        case STUDENT: {
                            StudentPackage studentPackage = gson.fromJson(rxJson, StudentPackage.class);
                            model.addStudent(studentPackage.getStudent());
                            out.println(gson.toJson(new StringPackage(NetworkType.NAME, studentPackage.getStudent().getName())));
                            break;
                        }
                        case ERROR: {
                            out.println(rxJson);
                        }
                    }
                } catch (Exception e) {
                    out.println(gson.toJson(new StringPackage(NetworkType.ERROR, e.getMessage())));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
                if (e.getMessage().equals("Connection reset")) stop();
            }
        }
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        if ("add".equals(evt.getPropertyName())) {
            out.println(gson.toJson(new StudentPackage((Student) evt.getNewValue())));
        }
    }
}
