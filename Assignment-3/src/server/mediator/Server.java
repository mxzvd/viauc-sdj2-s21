package server.mediator;

import server.model.Model;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

public class Server {

    public Server(Model model) throws RemoteException, MalformedURLException {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
        } catch (ExportException e) {
            System.out.println("\u001b[35mRegistry already started.\u001b[39m");
        }
        new UnauthorizedInstance(model);
        System.out.println("\u001b[35mServer started.\u001b[39m");
        loadDummyUsers(model);
    }

    private void loadDummyUsers(Model model) {
        try {
            AuthorizedInstance.getInstance(model, "Bob", "1234");
            AuthorizedInstance.getInstance(model, "Jack", "4321");
            AuthorizedInstance.getInstance(model, "Steve", "1234");

        } catch (Exception e) {
            System.out.println("\u001b[35mCould not load dummy users.\u001b[39m");
            System.err.println(e.getMessage());
        }
    }
}
