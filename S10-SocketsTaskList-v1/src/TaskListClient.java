import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TaskListClient {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner input;

    public TaskListClient(String client, int port) throws IOException {
        Socket socket = new Socket(client, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        input = new Scanner(System.in);
        execute();
        socket.close();
    }

    private void execute() throws IOException {
        while (true) {
            System.out.println("1) Add a task");
            System.out.println("2) Get a task");
            System.out.println("3) Get task list size");
            System.out.println("Default) Exit");
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1: {
                    out.writeUTF("ADD");
                    System.out.print("Enter the task text: ");
                    out.writeUTF(input.nextLine());
                    System.out.print("Enter the estimated time: ");
                    out.writeLong(input.nextLong());
                    if (in.readUTF().equals("ADD")) System.out.println("\u001b[35mServer> Task has been added.\u001b[39m");
                    break;
                }
                case 2: {
                    out.writeUTF("GET");
                    String response = in.readUTF();
                    if (!response.equals("ERROR")) {
                        System.out.println("\u001b[35mServer> " + response + " : " + in.readLong() + "\u001b[39m");
                    } else {
                        System.out.println("\u001b[35mServer> No tasks are registered in the list.\u001b[39m");
                    }
                    break;
                }
                case 3: {
                    out.writeUTF("SIZE");
                    System.out.println("\u001b[35mServer> " + in.readInt() + "\u001b[39m");
                    break;
                }
                default: {
                    out.writeUTF("");
                    if (in.readUTF().equals("EXIT")) System.out.println("\u001b[35mServer> Connection closed.\u001b[39m");
                    return;
                }
            }
            System.out.println();
        }
    }
}
