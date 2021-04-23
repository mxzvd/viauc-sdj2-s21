package client;

import server.RemoteMessageList;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RmiClient {

    private RemoteMessageList server;

    public RmiClient() {
        try {
            server = (RemoteMessageList) Naming.lookup("rmi://localhost:1099/AddMessage");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void send(String text) throws RemoteException {
        server.addMessage(text);
    }
}
