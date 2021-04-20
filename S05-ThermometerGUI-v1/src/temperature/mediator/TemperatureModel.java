package temperature.mediator;

import temperature.model.Temperature;
import java.beans.PropertyChangeListener;

public interface TemperatureModel extends NamedPropertyChangeSubject {
    void addTemperature(String id, double temperature);
    Temperature getLastInsertedTemperature();
    Temperature getLastInsertedTemperature(String id);
    void addListener(String propertyName, PropertyChangeListener listener);
    void removeListener(String propertyName, PropertyChangeListener listener);
}
