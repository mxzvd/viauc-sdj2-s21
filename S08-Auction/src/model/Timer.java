package model;

import utility.NamedPropertyChangeSubject;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer implements Runnable, NamedPropertyChangeSubject {
    private int timerSeconds;
    private PropertyChangeSupport property;

    public Timer(int timerSeconds) {
        this.timerSeconds = timerSeconds;
        property = new PropertyChangeSupport(this);
    }

    public int getTimerSeconds() {
        return timerSeconds;
    }

    @Override
    public void run() {
        LocalTime time = LocalTime.ofSecondOfDay(timerSeconds);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        while (timerSeconds >= 0) {
            property.firePropertyChange("time", timerSeconds, time.format(timeFormatter));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
            time = time.minusSeconds(1);
            timerSeconds--;
        }
        property.firePropertyChange("end", null, 0);
        PropertyChangeListener[] listeners = property.getPropertyChangeListeners();
        for (int i = 0; i < listeners.length; i++) {
            removeListener("time", listeners[i]);
            removeListener("end", listeners[i]);
        }
    }

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        property.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        property.removePropertyChangeListener(propertyName, listener);
    }
}