package server;

import client.RemoteSender;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMessageList extends Remote {
    void addMessage(String message, RemoteSender remote) throws RemoteException;
}
