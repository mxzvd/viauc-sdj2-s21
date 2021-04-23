package server;

import utility.observer.subject.RemoteSubject;
import java.rmi.RemoteException;

public interface RemoteInterface extends RemoteSubject<Task, Task> {
    void add(Task task) throws RemoteException;
    Task get() throws RemoteException;
    int size() throws RemoteException;
}
