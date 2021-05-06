package model;

import utility.UnnamedPropertyChangeSubject;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class User implements Colleague, UnnamedPropertyChangeSubject {

    private Mediator mediator;
    private PropertyChangeSupport property;

    public User(Mediator chat) {
        mediator = chat;
        property = new PropertyChangeSupport(this);
    }

    @Override
    public void receiveAffiliatedData(ArrayList<Message> data) {
        property.firePropertyChange("requestData", mediator.getUUID(), data);
    }

    @Override
    public void receiveMessage(Message message) {
        property.firePropertyChange("message", mediator.getUUID(), message);
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
