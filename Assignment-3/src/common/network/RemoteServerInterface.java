package common.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServerInterface extends Remote {
    void register(String username, String password) throws RemoteException;
    RemoteServerInterface login(String username, String password, RemoteClientInterface user) throws RemoteException;
    void createChat(String chatName, RemoteClientInterface user) throws RemoteException;
    void joinChat(String uuid, RemoteClientInterface user) throws RemoteException;
    void sendMessage(String uuid, String message, String userIP) throws RemoteException;
}
