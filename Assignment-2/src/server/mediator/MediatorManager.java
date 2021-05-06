package mediator;

import model.Model;
import java.io.IOException;

public class MediatorManager implements mediator.Mediator {

    private Server server;

    public MediatorManager(Model model) {
        try {
            loadDummyUsers(model);
            server = new Server(model);
            new Thread(server).start();
        } catch (IOException e) {
            System.out.println("\u001b[35mServer could not be started.\u001b[39m");
            System.err.println(e.getMessage());
        }
    }

    private void loadDummyUsers(Model model) {
        try {
            AuthorizedState.getInstance(model, "Bob", "1234");
            AuthorizedState.getInstance(model, "Jack", "4321");
            AuthorizedState.getInstance(model, "Steve", "1234");

        } catch (Exception e) {
            System.out.println("\u001b[35mCould not load dummy users.\u001b[39m");
            System.err.println(e.getMessage());
        }
    }

    public void stop() {
        try {
            server.close();
        } catch (IOException e) {
            System.out.println("\u001b[35mServer could not be closed.\u001b[39m");
            System.err.println(e.getMessage());
        }
    }
}
