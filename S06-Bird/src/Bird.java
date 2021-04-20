import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Bird implements NamedPropertyChangeSubject {

    private PropertyChangeSupport property;

    public Bird() {
        property = new PropertyChangeSupport(this);
    }

    @Override public void addListener(String propertyName, PropertyChangeListener listener) {
        property.addPropertyChangeListener(propertyName, listener);
    }

    @Override public void removeListener(String propertyName, PropertyChangeListener listener) {
        property.removePropertyChangeListener(propertyName, listener);
    }

    public void fly() {
        System.out.println("Peacock flashes its wings");
        property.firePropertyChange("birdIsFlying", 0, 1);
    }

    public void sing() {
        System.out.println("Peacock whistles");
        property.firePropertyChange("birdIsSinging", 0, 1);

    }
}
