package client.model;

import client.mediator.Client;
import common.network.Message;
import common.utility.observer.listener.GeneralListener;
import java.rmi.NoSuchObjectException;
import java.rmi.server.UnicastRemoteObject;

public class ModelManager implements Model {

    private Client client;

    public ModelManager() {
        try {
            client = new Client(this);
        } catch (Exception e) {
            System.err.println("Could not connect to the server.");
            System.err.println(e.getMessage());
        }
    }

    public void addListenerOfBroadcasts(GeneralListener<String, Message> listener) {
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

    public void stop() {
        try {
            UnicastRemoteObject.unexportObject(client, true);
        } catch (NoSuchObjectException e) {
            // Do nothing.
        }
    }
}
