package server.mediator;

import common.network.RemoteClientInterface;
import common.network.RemoteServerInterface;
import server.model.Model;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UnauthorizedInstance implements RemoteServerInterface {

    private Model model;

    public UnauthorizedInstance(Model model) throws RemoteException, MalformedURLException {
        this.model = model;
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("access", this);
    }

    @Override
    public void register(String username, String password) throws RemoteException {
        try {
            AuthorizedInstance.getInstance(model, username, password);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public RemoteServerInterface login(String username, String password, RemoteClientInterface user) throws RemoteException {
        RemoteServerInterface toReturn;
        try {
            toReturn = AuthorizedInstance.getInstance(null, username, password);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
        model.reestablishConnection(username, user);
        return toReturn;
    }

    @Override
    public void createChat(String chatName, RemoteClientInterface user) throws RemoteException {
        throw new RemoteException("Log in to perform this request.");
    }

    @Override
    public void joinChat(String uuid, RemoteClientInterface user) throws RemoteException {
        throw new RemoteException("Log in to perform this request.");
    }

    @Override
    public void sendMessage(String uuid, String message, String userIP) throws RemoteException {
        throw new RemoteException("Log in to perform this request.");
    }
}
