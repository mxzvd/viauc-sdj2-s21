package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiTaskServer implements RemoteTaskList {

    TaskList taskList;

    public RmiTaskServer() {
        taskList = new TaskList();
        startRegistry();
        try {
            startServer();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void startRegistry() {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            System.out.println("Registry Started.");
        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    private void startServer() throws RemoteException, MalformedURLException {
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("TaskList", this);
    }

    @Override public void add(Task task) {
        taskList.addTask(task);
    }

    @Override public Task get() {
        return taskList.getAndRemoveNextTask();
    }

    @Override public int size() {
        return taskList.size();
    }
}
