package model;

import mediator.ExercisesClient;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ModelManager implements Model, PropertyChangeListener {

    private ExercisesClient client;
    private PropertyChangeSupport property;
    public static final String HOST = "localhost";
    public static final int PORT = 2910;

    public ModelManager() throws IOException {
        client = new ExercisesClient(this, HOST, PORT);
        property = new PropertyChangeSupport(this);
        client.addListener(this);
    }

    @Override
    public ArrayList<Exercise> getAllExercises() {
        return client.getAllExercises();
    }

    @Override
    public ArrayList<Exercise> getExercises(boolean completed) {
        return client.getExercises(completed);
    }

    @Override
    public Exercise getExercise(String number) throws Exception {
        return client.getExercise(number);
    }

    @Override
    public Exercise removeExercise(String number) throws Exception {
        return client.removeExercise(number);
    }

    @Override
    public Exercise editExercise(String number, Exercise exercise) throws Exception {
        return client.editExercise(number, exercise);
    }

    @Override
    public void addExercise(Exercise exercise) throws Exception {
        client.addExercise(exercise);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        property.firePropertyChange(evt);
    }
}
