package model;

import java.beans.PropertyChangeListener;

public class ModelManager implements Model {

    private MediatorFactory chatFactory;

    public ModelManager() {
        chatFactory = new ChatFactory();
    }

    @Override
    public void addListenerToUser(String username, PropertyChangeListener listener) {
        for (Mediator mediator : chatFactory.getAllMediators()) {
            try {
                mediator.getUserByName(username).addListener(listener);
            } catch (Exception e) {
                // Do nothing.
            }
        }
    }

    @Override
    public void sendAllAffiliatedDataTo(String username) {
        for (Mediator mediator : chatFactory.getAllMediators()) {
            try {
                mediator.sendAffiliatedData(username);
            } catch (Exception e) {
                // Do nothing.
            }
        }
    }

    @Override
    public void sendActiveUsersOfChatTo(String uuid, String username) throws Exception {
        chatFactory.getMediator(uuid, null).sendActiveUsersTo(username);
    }

    @Override
    public String createMediator(String mediatorName) throws Exception {
        return chatFactory.getMediator("", mediatorName).getUUID();
    }

    @Override
    public void messageChat(String uuid, String username, String userIP, String message) throws Exception {
        chatFactory.getMediator(uuid, null).sendMessage(username, userIP, message);
    }

    @Override
    public void addUserToChat(String uuid, String username, PropertyChangeListener listener) throws Exception {
        chatFactory.getMediator(uuid, null).addUser(username);
        chatFactory.getMediator(uuid, null).getUserByName(username).addListener(listener);
        chatFactory.getMediator(uuid, null).sendMessage(username, "0.0.0.0", username + " just joined the chat.");
    }

    @Override
    public void removeUserFromChat(String uuid, String username, PropertyChangeListener listener) throws Exception {
        chatFactory.getMediator(uuid, null).getUserByName(username).removeListener(listener);
        chatFactory.getMediator(uuid, null).removeUser(username);
        chatFactory.getMediator(uuid, null).sendMessage(username, "0.0.0.0", username + " left the chat.");
    }
}
