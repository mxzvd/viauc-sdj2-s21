package external;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ObservableClock implements Runnable, UnnamedPropertyChangeSubject {

    private DateTimeFormatter timeFormatter;
    private PropertyChangeSupport property;

    public ObservableClock() {
        property = new PropertyChangeSupport(this);
    }

    @Override
    public void run() {
        while (true) {
            LocalTime time = LocalTime.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = time.format(timeFormatter);
            property.firePropertyChange("anything", "gdfgd", timeString);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
