package client;

import com.google.gson.Gson;
import server.Task;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TaskListClient {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Scanner input;
    private Gson gson;
    private String receivedString;

    public TaskListClient(String client, int port) throws IOException {
        Socket socket = new Socket(client, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())) {
            @Override public String readLine() throws IOException {
                String rxData = super.readLine();
                System.out.println("> \u001b[36mRX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] > " + rxData);
                return rxData;
            }
        };
        out = new PrintWriter(socket.getOutputStream(), true) {
            @Override public void println(String txData) {
                super.println(txData);
                System.out.println("< \u001b[36mTX: \u001b[39m[\u001b[32m" + java.time.LocalDateTime.now().toString().replace("T", "\u001b[39m | \u001b[32m") + "\u001b[39m | \u001b[36m" + socket.getInetAddress().getHostAddress() + "\u001b[39m] < " + txData);
            }
        };
        input = new Scanner(System.in);
        gson = new Gson();
        receivedString = null;
        new Thread(new TaskListClientReceiver(this, in)).start();
        execute();
        in.close();
        out.close();
        socket.close();
        input.close();
    }

    public void receive(String s) {
        switch (s) {
            case "ADD":
            case "REMOVE": {
                System.out.println("\u001b[35mReceived broadcast from server> " + s + "\u001b[39m");
                System.out.flush();
                break;
            }
            default: {
                receivedString = s;
                // See the comment down below first.
                synchronized (this) {
                    notify();
                }
            }
        }
    }

    // Has to be synchronized otherwise wait() method will throw errors.
    synchronized private void execute() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1) Add a task");
            System.out.println("2) Get a task");
            System.out.println("3) Get task list size");
            System.out.println("Default) Exit");
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1: {
                    out.println("ADD");
                    System.out.print("Enter the task text: ");
                    String taskText = input.nextLine();
                    System.out.print("Enter the estimated time: ");
                    long taskTime = input.nextLong();
                    out.println(gson.toJson(new Task(taskText, taskTime)));
                    break;
                }
                case 2: {
                    out.println("GET");
                    try {
                        // Here (and in all of the cases down below) this code here makes the thread wait.
                        // We need this because the receiving thread does not update the receivedString variable instantaneously.
                        // However it will wait indefinitely once wait() method is called.
                        // Therefore we have to use the notify() method from above to let the thread now the receivedString is updated and no more waiting is necessary.
                        while (receivedString == null) wait();
                        if (!receivedString.equals("ERROR")) {
                            Task task = gson.fromJson(receivedString, Task.class);
                            System.out.println("\u001b[35mServer> " + task.getText() + " : " + task.getEstimatedTime() + "\u001b[39m");
                        } else {
                            System.out.println("\u001b[35mServer> No tasks are registered in the list.\u001b[39m");
                        }
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    receivedString = null;
                    break;
                }
                case 3: {
                    out.println("SIZE");
                    try {
                        while (receivedString == null) wait();
                        System.out.println("\u001b[35mServer> " + receivedString + "\u001b[39m");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    receivedString = null;
                    break;
                }
                default: {
                    out.println("EXIT");
                    try {
                        while (receivedString == null) wait();
                        if (receivedString.equals("EXIT")) System.out.println("\u001b[35mServer> Connection closed.\u001b[39m");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    return;
                }
            }
        }
    }
}
