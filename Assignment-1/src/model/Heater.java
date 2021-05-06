package model;

import utility.UnnamedPropertyChangeSubject;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// Heater class is a subject which will fire events whenever the it's power is changed.
public class Heater implements UnnamedPropertyChangeSubject {
    
    private HeaterState state;
    private int power;
    private PropertyChangeSupport property;

    public Heater() {
        state = new GenericPowerState();
        power = 0;
        property = new PropertyChangeSupport(this);
    }

    public void setState(HeaterState state) {
        this.state = state;
    }

    // Method used to set the heater's power by the states and which fires the events.
    public void offsetPowerBy(int units) {
        power += units;
        property.firePropertyChange("heater", power - units, power);
    }

    public int getPower() {
        return power;
    }

    public void increasePower() {
        state.powerUp(this);
    }

    public void decreasePower() {
        state.powerDown(this);
    }

    @Override public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
