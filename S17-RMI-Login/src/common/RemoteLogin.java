package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteLogin extends Remote {
    void login(String username, String password) throws RemoteException;
}
