package server;

import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteInterface {

    private PropertyChangeHandler<Task, Task> property;
    private TaskList taskList;

    public Server() throws RemoteException, MalformedURLException {
        property = new PropertyChangeHandler<>(this);
        taskList = new TaskList();
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
        } catch (ExportException e) {
            System.out.println("Registry already started.");
        }
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("TaskList", this);
        System.out.println("Server started.");
    }

    @Override
    public boolean addListener(GeneralListener<Task, Task> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<Task, Task> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);
    }

    @Override
    public void add(Task task) {
        taskList.addTask(task);
        property.firePropertyChange("task", task, task);
    }

    @Override
    public Task get() {
        return taskList.getAndRemoveNextTask();
    }

    @Override
    public int size() {
        return taskList.size();
    }
}
