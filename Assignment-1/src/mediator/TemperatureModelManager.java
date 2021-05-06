package mediator;

import model.Heater;
import model.Temperature;
import model.TemperatureList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// Subject class which will fire events whenever a new temperature gets added.
public class TemperatureModelManager implements TemperatureModel {
    
    private Heater heater;
    private TemperatureList temperatureList;
    private PropertyChangeSupport property;

    public TemperatureModelManager() {
        heater = new Heater();
        temperatureList = new TemperatureList();
        property = new PropertyChangeSupport(this);
    }

    // Method to add temperatures which will also fire the event with the new temperature.
    @Override public synchronized void addTemperature(String id, double value, boolean measuredOutdoor) {
        Temperature temperature = new Temperature(id, value, measuredOutdoor);
        Temperature old = getLastInsertedTemperature(id);
        temperatureList.addTemperature(temperature);
        // Line below is just for debugging and verifying, not part of the system.
        System.out.println("--> " + temperature + " | Old: " + old);
        property.firePropertyChange("temperature", old, temperature);
    }

    @Override public Temperature getLastInsertedTemperature() {
        return temperatureList.getLastTemperature(null);
    }

    @Override public Temperature getLastInsertedTemperature(String id) {
        return temperatureList.getLastTemperature(id);
    }

    @Override public Heater getHeater() {
        return heater;
    }

    @Override public int getHeaterPower() {
        return heater.getPower();
    }

    @Override public void increaseHeaterPower() {
        heater.increasePower();
    }

    @Override public void decreaseHeaterPower() {
        heater.decreasePower();
    }

    @Override public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
