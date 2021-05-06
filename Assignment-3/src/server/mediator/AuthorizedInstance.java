package server.mediator;

import common.network.RemoteClientInterface;
import common.network.RemoteServerInterface;
import server.model.Model;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class AuthorizedInstance implements RemoteServerInterface {

    private static Map<String, AuthorizedInstance> allInstances = new HashMap<>();
    private Model model;
    private String username;
    private String password;

    private AuthorizedInstance(Model model, String username, String password) {
        this.model = model;
        this.username = username;
        this.password = password;
    }

    public static AuthorizedInstance getInstance(Model model, String username, String password) throws Exception {
        AuthorizedInstance instance = allInstances.get(username);
        if (instance == null) {
            // If user tries to log in and the username is not yet registered.
            if (model == null) throw new Exception("Invalid username or password.");
            // Otherwise user tries to register and the account is not yet registered so we create a new account.
            synchronized (allInstances) {
                if (allInstances.get(username) == null) {
                    instance = new AuthorizedInstance(model, username, password);
                    UnicastRemoteObject.exportObject(instance, 0);
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
    public void register(String username, String password) throws RemoteException {
        throw new RemoteException("You are already logged in.");
    }

    @Override
    public RemoteServerInterface login(String username, String password, RemoteClientInterface user) throws RemoteException {
        throw new RemoteException("You are already logged in.");
    }

    @Override
    public void createChat(String chatName, RemoteClientInterface user) throws RemoteException {
        try {
            joinChat(model.createMediator(chatName), user);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void joinChat(String uuid, RemoteClientInterface user) throws RemoteException {
        try {
            model.addUserToChat(uuid, username, user);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void sendMessage(String uuid, String message, String userIP) throws RemoteException {
        try {
            model.messageChat(uuid, username, userIP, message);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
