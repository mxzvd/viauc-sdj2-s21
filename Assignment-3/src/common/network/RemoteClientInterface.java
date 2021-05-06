package common.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClientInterface extends Remote {
    String getClientIP() throws RemoteException;
    void receiveMessage(String uuid, Message message) throws RemoteException;
}
