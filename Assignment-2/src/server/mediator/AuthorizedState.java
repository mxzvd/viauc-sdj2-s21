package mediator;

import model.Model;
import java.util.HashMap;
import java.util.Map;

public class AuthorizedState extends HandlerState {

    private static Map<String, AuthorizedState> allInstances = new HashMap<>();
    private String username;
    private String password;

    private AuthorizedState(Model model, String username, String password) {
        super(model);
        this.username = username;
        this.password = password;
    }

    public static AuthorizedState getInstance(Model model, String username, String password) throws Exception {
        AuthorizedState instance = allInstances.get(username);
        if (instance == null) {
            // If user tries to log in and the username is not yet registered.
            if (model == null) throw new Exception("Invalid username or password.");
            // Otherwise user tries to register and the account is not yet registered so we create a new account.
            synchronized (allInstances) {
                if (allInstances.get(username) == null) {
                    instance = new AuthorizedState(model, username, password);
                    allInstances.put(username, instance);
                    return instance;
                }
            }
        }
        // If user tries to register and username is already taken.
        if (model != null) throw new Exception("This username is already registered. Try another.");
        // User tries to log in but the passwords don't match.
        if (!instance.password.equals(password)) throw new Exception("Invalid username or password.");
        return instance;
    }

    @Override
    public boolean register(ClientHandler clientHandler, String username, String password) throws Exception {
        throw new Exception("You are already logged in.");
    }

    @Override
    public void login(ClientHandler clientHandler, String username, String password) throws Exception {
        throw new Exception("You are already logged in.");
    }

    @Override
    public void requestActiveUsersOfChat(String uuid) throws Exception {
        super.getModel().sendActiveUsersOfChatTo(uuid, username);
    }

    @Override
    public void createChat(ClientHandler clientHandler, String chatName) throws Exception {
        joinChat(super.getModel().createMediator(chatName), clientHandler);
    }

    @Override
    public void messageChat(String uuid, String message, String userIP) throws Exception {
        super.getModel().messageChat(uuid, username, userIP, message);
    }

    @Override
    public void joinChat(String uuid, ClientHandler clientHandler) throws Exception {
        super.getModel().addUserToChat(uuid, username, clientHandler);
    }
}
