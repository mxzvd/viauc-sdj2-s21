package client;

import server.RemoteTaskList;
import server.Task;
import java.rmi.Naming;
import java.util.Scanner;

public class RmiTaskClient {

    private RemoteTaskList server;

    public RmiTaskClient(String host) {
        try {
            server = (RemoteTaskList) Naming.lookup("rmi://" + host + ":1099/TaskList");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
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
                        return;
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
