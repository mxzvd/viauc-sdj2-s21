import client.model.Model;
import client.model.ModelManager;
import java.io.IOException;
import java.util.Scanner;

public class ClientTest {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Model model = null;
        try {
            model = new ModelManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                String username;
                do {
                    System.out.print("Enter a username: ");
                    username = input.nextLine();
                } while (username == null || username.isEmpty());

                String password;
                do {
                    System.out.print("Enter a password: ");
                    password = input.nextLine();
                } while (password == null || password.isEmpty());

                model.login(username, password);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
