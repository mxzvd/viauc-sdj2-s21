package model;

import mediator.Client;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ModelManager implements Model {

    public static final String HOST = "localhost";
    public static final int PORT = 2989;

    private Client client;

    public ModelManager() throws IOException {
        client = new Client(this, HOST, PORT);
    }

    public void addListenerOfBroadcasts(PropertyChangeListener listener) {
        client.addListener(listener);
    }

    @Override
    public void register(String username, String password) throws Exception {
        client.register(username, password);
    }

    @Override
    public void login(String username, String password) throws Exception {
        client.login(username, password);
    }

    @Override
    public void createChat(String chatName) throws Exception {
        client.createChat(chatName);
    }

    @Override
    public void joinChat(String uuid) throws Exception {
        client.joinChat(uuid);
    }

    @Override
    public void sendMessage(String uuid, String message) throws Exception {
        client.sendMessage(uuid, message);
    }
}
