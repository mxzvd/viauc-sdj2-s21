package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model {

    private Converter converter;
    private PropertyChangeSupport property;

    public ModelManager() {
        this.converter = new Converter();
        this.property = new PropertyChangeSupport(this);
    }

    @Override
    public String convert(String source) {
        String reply = converter.toUpperCase(source);
        addLog("Converting: " + source);
        return reply;
    }

    @Override
    public void addLog(String log) {
        converter.addLog(log);
        int size = converter.getLogSize();
        property.firePropertyChange("log", "", size + ": " + log);
    }

    @Override
    public void addMessage(String message) {
        property.firePropertyChange("broadcast", "", message);
        addLog("Message: " + message);
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
