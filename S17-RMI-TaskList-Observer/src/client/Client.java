package client;

import server.RemoteInterface;
import server.Task;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client implements RemoteListener<Task, Task> {

    private RemoteInterface server;

    public Client(String host) throws RemoteException, MalformedURLException, NotBoundException {
        UnicastRemoteObject.exportObject(this, 0);
        server = (RemoteInterface) Naming.lookup("rmi://" + host + ":1099/TaskList");
        server.addListener(this);
    }

    public void start() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("1) Add a task");
            System.out.println("2) Get a task");
            System.out.println("3) Get task list size");
            System.out.println("Default) Exit");
            int option = input.nextInt();
            input.nextLine();
            try {
                switch (option) {
                    case 1: {
                        System.out.print("Enter the task text: ");
                        String taskText = input.nextLine();
                        System.out.print("Enter the estimated time: ");
                        long taskTime = input.nextLong();
                        server.add(new Task(taskText, taskTime));
                        break;
                    }
                    case 2: {
                        System.out.println(server.get());
                        break;
                    }
                    case 3: {
                        System.out.println(server.size());
                        break;
                    }
                    default: {
                        UnicastRemoteObject.unexportObject(this, true);
                        return;
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void propertyChange(ObserverEvent<Task, Task> evt) {
        System.out.println("Task added: " + evt.getValue2());
    }
}
