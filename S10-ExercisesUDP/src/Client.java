import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        final int PORT = 9876;
        final String HOST = "localhost";
        Scanner input = new Scanner(System.in);
        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName(HOST);
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        System.out.print("Write a line for the server: ");
        String sentence = input.nextLine();
        System.out.println("Client> " + sentence);
        sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PORT);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);
        String modifiedStc = new String(receivePacket.getData()).trim();
        System.out.println("Server> " + modifiedStc);

        clientSocket.close();
    }
}
