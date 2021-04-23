package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMessageList extends Remote {
    void addMessage(String message) throws RemoteException;
}
