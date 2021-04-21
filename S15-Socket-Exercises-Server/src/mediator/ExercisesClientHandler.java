package mediator;

import com.google.gson.Gson;
import model.Exercise;
import model.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ExercisesClientHandler implements Runnable, PropertyChangeListener {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean running;
    private Gson gson;
    private Model model;

    public ExercisesClientHandler(Socket socket, Model model) throws IOException {
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
        this.model = model;
        model.addListener(this);
    }

    public void close() {
        try {
            running = false;
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override public void run() {
        running = true;
        while (running) {
            try {
                String rxJson = in.readLine();
                ExercisePackage rxPackage = gson.fromJson(rxJson, ExercisePackage.class);
                try {
                    switch (rxPackage.getType()) {
                        case "All" : {
                            out.println(gson.toJson(new ExerciseListPackage("All", model.getAllExercises())));
                            break;
                        }
                        case "Add" : {
                            model.addExercise(rxPackage.getExercise());
                            break;
                        }
                        case "Edit" : {
                            model.editExercise(rxPackage.getNumber(), rxPackage.getExercise());
                            break;
                        }
                        case "Remove" : {
                            Exercise removedExercise = model.removeExercise(rxPackage.getNumber());
                            if (removedExercise == null) throw new Exception("No exercise has been removed.");
                            break;
                        }
                        case "Get" : {
                            Exercise toReturn = model.getExercise(rxPackage.getNumber());
                            out.println(gson.toJson(new ExercisePackage("Get", toReturn, rxPackage.getNumber())));
                        }
                    }
                } catch (Exception e) {
                    out.println(gson.toJson(new ExercisePackage("Error", e.getMessage())));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
                if (e.getMessage().equals("Connection reset")) close();
            }
        }
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "Add" : {
                out.println(gson.toJson(new ExercisePackage("Add", (Exercise) evt.getNewValue(), null)));
                break;
            }
            case "Edit" : {
                out.println(gson.toJson(new ExercisePackage("Edit", (Exercise) evt.getNewValue(), evt.getOldValue().toString())));
                break;
            }
            case "Remove" : {
                out.println(gson.toJson(new ExercisePackage("Remove", null, evt.getOldValue().toString())));
            }
        }
    }
}
