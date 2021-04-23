package client.model;

import client.mediator.Client;
import client.mediator.ServerModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.rmi.server.UnicastRemoteObject;

public class ModelManager implements Model {

    private ServerModel serverModel;
    private PropertyChangeSupport property;

    public ModelManager() throws IOException {
        property = new PropertyChangeSupport(this);
        try {
            serverModel = new Client(this);
        } catch (Exception e) {
            System.err.println("Could not connect to the server");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public synchronized String convert(String source) throws IOException, InterruptedException {
        return serverModel.convert(source);
    }

    @Override
    public void setMessage(String message) {
        property.firePropertyChange("broadcast", "  ffads ", message);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }

    public void stop() {
        try {
            UnicastRemoteObject.unexportObject(serverModel, true);
        } catch (NoSuchObjectException e) {
            // Do nothing.
        }
    }
}
