package client.mediator;

import client.model.Model;
import common.network.Message;
import common.network.RemoteClientInterface;
import common.network.RemoteServerInterface;
import common.utility.observer.listener.GeneralListener;
import common.utility.observer.subject.LocalSubject;
import common.utility.observer.subject.PropertyChangeHandler;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client implements Model, RemoteClientInterface, LocalSubject<String, Message> {

    private RemoteServerInterface server;
    private PropertyChangeHandler<String, Message> property;
    private Model model;

    public Client(Model model) throws RemoteException, MalformedURLException, NotBoundException {
        property = new PropertyChangeHandler<>(this);
        this.model = model;
        UnicastRemoteObject.exportObject(this, 0);
        server = (RemoteServerInterface) Naming.lookup("rmi://127.0.0.1:1099/access");
    }

    @Override
    public boolean addListener(GeneralListener<String, Message> listener, String... propertyNames) {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<String, Message> listener, String... propertyNames) {
        return property.removeListener(listener, propertyNames);
    }

    @Override
    public void register(String username, String password) throws Exception {
        if (username == null || username.isEmpty()) throw new Exception("Type in a username.");
        if (password == null || password.isEmpty()) throw new Exception("Type in a password.");
        server.register(username, password);
    }

    @Override
    public void login(String username, String password) throws Exception {
        if (username == null || username.isEmpty()) throw new Exception("Type in a username.");
        if (password == null || password.isEmpty()) throw new Exception("Type in a password.");
        server = server.login(username, password, this);
    }

    @Override
    public void createChat(String chatName) throws Exception {
        if (chatName == null || chatName.isEmpty()) throw new Exception("Type in a name of the chat to be created.");
        server.createChat(chatName, this);
    }

    @Override
    public void joinChat(String uuid) throws Exception {
        if (uuid == null || uuid.isEmpty()) throw new Exception("Type in the uuid of the chat to join.");
        server.joinChat(uuid, this);
    }

    @Override
    public void sendMessage(String uuid, String message) throws Exception {
        if (uuid == null || uuid.isEmpty()) throw new Exception("Select a chat for the message to be sent.");
        if (message == null || message.isEmpty()) throw new Exception("Type in a message to be sent.");
        server.sendMessage(uuid, message, getClientIP());
    }

    @Override
    public String getClientIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            return "unresolved";
        }
    }

    @Override
    public void receiveMessage(String uuid, Message message) throws RemoteException {
        property.firePropertyChange("message", uuid, message);
    }
}
