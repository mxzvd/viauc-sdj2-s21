package mediator;

import com.google.gson.Gson;
import model.Exercise;
import model.Model;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

public class ExercisesClient implements Model {

    private Model model;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson;
    private ExerciseListPackage receivedListPackages;
    private ArrayList<ExercisePackage> receivedExercisePackages;
    private boolean waiting;
    private PropertyChangeSupport property;

    public ExercisesClient(Model model, String host, int port) throws IOException {
        this.model = model;
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
        gson = new Gson();
        receivedListPackages = null;
        receivedExercisePackages = new ArrayList<>();
        waiting = false;
        property = new PropertyChangeSupport(this);
        Thread readerThread = new Thread(new ExercisesClientReader(this, in));
        readerThread.setDaemon(true);
        readerThread.start();
    }

    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public synchronized void receive(String s) {
        if (gson.fromJson(s, Map.class).get("type").equals("All")) {
            receivedListPackages = gson.fromJson(s, ExerciseListPackage.class);
            notify();
            return;
        }
        ExercisePackage rxPackage = gson.fromJson(s, ExercisePackage.class);
        if (waiting) {
            receivedExercisePackages.add(rxPackage);
            notify();
        } else {
            property.firePropertyChange(rxPackage.getType(), rxPackage.getNumber(), rxPackage.getExercise());
        }
    }

    private synchronized ExercisePackage waitingForReply() {
        waiting = true;
        while (receivedExercisePackages.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        waiting = false;
        ExercisePackage toReturn = receivedExercisePackages.get(0);
        receivedExercisePackages.remove(0);
        return toReturn;
    }

    @Override
    public synchronized ArrayList<Exercise> getAllExercises() {
        out.println(gson.toJson(new ExercisePackage("All", null, null)));
        while (receivedListPackages == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        ArrayList<Exercise> toReturn = new ArrayList<>(receivedListPackages.getExercises());
        receivedListPackages = null;
        return toReturn;
    }

    @Override
    public ArrayList<Exercise> getExercises(boolean completed) {
        ArrayList<Exercise> toReturn = new ArrayList<>();
        for (Exercise exercise : getAllExercises()) if (exercise.isCompleted()) toReturn.add(exercise);
        return toReturn;
    }

    @Override
    public Exercise getExercise(String number) throws Exception {
        out.println(gson.toJson(new ExercisePackage("Get", null, number)));
        ExercisePackage receivedPackage = waitingForReply();
        if (receivedPackage.getType().equals("Error")) throw new Exception(receivedPackage.getError());
        return receivedPackage.getExercise();
    }

    @Override
    public Exercise removeExercise(String number) throws Exception {
        Exercise toReturn = getExercise(number);
        out.println(gson.toJson(new ExercisePackage("Remove", null, number)));
        ExercisePackage receivedPackage = waitingForReply();
        if (receivedPackage.getType().equals("Error")) {
            throw new Exception(receivedPackage.getError());
        }
        property.firePropertyChange(receivedPackage.getType(), receivedPackage.getNumber(), receivedPackage.getExercise());
        return toReturn;
    }

    @Override
    public void addExercise(Exercise exercise) throws Exception {
        out.println(gson.toJson(new ExercisePackage("Add", exercise, null)));
        ExercisePackage receivedPackage = waitingForReply();
        if (receivedPackage.getType().equals("Error")) throw new Exception(receivedPackage.getError());
        property.firePropertyChange(receivedPackage.getType(), receivedPackage.getNumber(), receivedPackage.getExercise());
    }

    @Override
    public Exercise editExercise(String number, Exercise exercise) throws Exception {
        out.println(gson.toJson(new ExercisePackage("Edit", exercise, number)));
        ExercisePackage receivedPackage = waitingForReply();
        if (receivedPackage.getType().equals("Error")) throw new Exception(receivedPackage.getError());
        return receivedPackage.getExercise();
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }
}
