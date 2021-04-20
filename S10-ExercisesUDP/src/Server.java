import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static void main(String[] args) throws IOException {

        final int PORT = 9876;
        DatagramSocket serverSocket = new DatagramSocket(PORT);

        while (true) {

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData()).trim();

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            System.out.println("Client> " + sentence);
            String capitalizedSentence = sentence.toUpperCase();
            System.out.println("Server> " + capitalizedSentence);
            byte[] sendData = new byte[1024];
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket= new DatagramPacket(sendData,sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
