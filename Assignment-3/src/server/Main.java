package server;

import server.mediator.Server;
import server.model.Model;
import server.model.ModelManager;

public class Main {
    public static void main(String[] args) {
        Model model = new ModelManager();
        try {
            Server server = new Server(model);
        } catch (Exception e) {
            System.err.println("Could not start the server.");
            System.err.println(e.getMessage());
        }
    }
}
