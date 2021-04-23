package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RmiServer implements RemoteMessageList {

    private ArrayList<String> messageList;

    public RmiServer() {
        messageList = new ArrayList<>();
    }

    public void start() throws RemoteException, MalformedURLException {
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("AddMessage", this);
    }

    @Override public void addMessage(String message) {
        messageList.add(message);
        System.out.println("Message Added: " + message);
    }
}
