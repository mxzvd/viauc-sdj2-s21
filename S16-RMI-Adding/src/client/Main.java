package client;

import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws RemoteException {
        RmiClient client = new RmiClient();
        Scanner in = new Scanner(System.in);

        String message;
        do {
            System.out.print("Enter the message to be sent: ");
            message = in.nextLine();
            client.send(message);
        } while (!message.equals("quit"));
    }
}
