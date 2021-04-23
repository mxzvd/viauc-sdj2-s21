package server.mediator;

import common.network.RemoteServerInterface;
import common.utility.observer.listener.GeneralListener;
import common.utility.observer.subject.PropertyChangeHandler;
import server.model.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteServerInterface, PropertyChangeListener {

    private PropertyChangeHandler<String, String> property;
    private Model model;

    public Server(Model model) throws RemoteException, MalformedURLException {
        property = new PropertyChangeHandler<>(this);
        this.model = model;
        model.addListener(this);
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
        } catch (ExportException e) {
            System.out.println("Registry already started.");
        }
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("access", this);
        System.out.println("Server started.");
    }

    @Override
    public boolean addListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, String> listener, String... propertyNames) throws RemoteException {
        return property.removeListener(listener, propertyNames);
    }

    @Override
    public String request(String messageToConvert) throws RemoteException {
        try {
            return model.convert(messageToConvert);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("broadcast")) property.firePropertyChange("broadcast", evt.getOldValue().toString(), evt.getNewValue().toString());
    }
}
