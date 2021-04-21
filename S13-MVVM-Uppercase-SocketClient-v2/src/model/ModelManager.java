package model;

import mediator.ServerModel;
import mediator.UppercaseClient;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ModelManager implements Model {

    private ServerModel serverModel;
    private PropertyChangeSupport property;

    public ModelManager() throws IOException {
        serverModel = new UppercaseClient(this);
        serverModel.connect();
        property = new PropertyChangeSupport(this);
    }

    @Override
    public synchronized String convert(String source) throws IOException, InterruptedException {
        return serverModel.convert(source);
    }

    public void setMessage(String message) {
        property.firePropertyChange("broadcast", null, message);
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
