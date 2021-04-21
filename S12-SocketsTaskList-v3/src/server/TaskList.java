package server;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TaskList implements UnnamedPropertyChangeSubject {

    private ArrayList<Task> tasks;
    private PropertyChangeSupport property;

    public TaskList() {
        tasks = new ArrayList<>();
        property = new PropertyChangeSupport(this);
    }

    public synchronized void add(Task task) {
        tasks.add(task);
        property.firePropertyChange("ADD", "", task);
    }

    public synchronized Task getAndRemoveNextTask() {
        if (tasks.size() > 0) {
            Task toReturn = tasks.remove(0);
            property.firePropertyChange("REMOVE", "", toReturn);
            return toReturn;
        }
        return null;
    }

    public synchronized int size() {
        return tasks.size();
    }

    @Override public synchronized String toString() {
        return "Tasks=" + tasks;
    }

    @Override public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
