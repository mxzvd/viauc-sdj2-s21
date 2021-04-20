import java.beans.PropertyChangeListener;

public interface UnnamedPropertySubject {
    void addListener(PropertyChangeListener listener);
    void removeListener(PropertyChangeListener listener);
}
