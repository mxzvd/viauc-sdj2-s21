package client.mediator;

import client.model.Model;
import common.RemoteLogin;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class UserClient implements Model {

    private RemoteLogin server;

    public UserClient() {
        try {
            server = (RemoteLogin) Naming.lookup("rmi://localhost:1099/Login");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override public void login(String userName, String password) throws RemoteException {
        server.login(userName, password);
    }
}
