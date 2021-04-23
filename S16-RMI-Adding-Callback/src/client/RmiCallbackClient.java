package client;

import server.RemoteMessageList;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiCallbackClient implements RemoteSender {

    private RemoteMessageList server;

    public RmiCallbackClient() throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        try {
            server = (RemoteMessageList) Naming.lookup("rmi://localhost:1099/AddMessage");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void send(String text) throws RemoteException {
        server.addMessage(text, this);
    }

    public void replyMessage(String message) {
        System.out.println(message);
    }
}
