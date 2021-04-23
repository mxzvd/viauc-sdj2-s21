package server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

public class Main {

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        startRegistry();
        RmiServer server = new RmiServer();
        server.start();
        System.out.println("Server started.");
    }

    public static void startRegistry() throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            System.out.println("Registry Started.");
        } catch (ExportException e) {
            System.out.println(e.getMessage());
        }
    }
}
