package server.mediator;

import common.RemoteLogin;
import server.model.Model;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class UserServer implements RemoteLogin {

    private Model model;

    public UserServer(Model model) throws RemoteException, MalformedURLException {
        this.model = model;
        Registry reg = LocateRegistry.createRegistry(1099);
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("Login", this);
        System.out.println("Server started.");
    }

    @Override public void login(String username, String password) {
        model.addUser(username, password);
    }
}
