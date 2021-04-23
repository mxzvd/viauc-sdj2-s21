package client.mediator;

import client.model.Model;
import common.network.RemoteServerInterface;
import common.utility.observer.event.ObserverEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client implements ServerModel {

    private RemoteServerInterface server;
    private Model model;

    public Client(Model model) throws MalformedURLException, NotBoundException, RemoteException {
        this.model = model;
        UnicastRemoteObject.exportObject(this, 0);
        server = (RemoteServerInterface) Naming.lookup("rmi://localhost:1099/access");
        server.addListener(this);
    }

    @Override
    public String convert(String source) throws IOException, InterruptedException {
        return server.request(source);
    }

    @Override
    public void propertyChange(ObserverEvent<String, String> event) throws RemoteException {
        model.setMessage(event.getValue2());
    }
}
