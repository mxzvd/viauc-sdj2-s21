package server.model;

import common.network.RemoteClientInterface;

public class ModelManager implements Model {

    private MediatorFactory chatFactory;

    public ModelManager() {
        chatFactory = new ChatFactory();
    }

    @Override
    public void reestablishConnection(String username, RemoteClientInterface user) {
        for (Mediator mediator : chatFactory.getAllMediators()) {
            try {
                mediator.reestablishConnection(username, user);
            } catch (Exception e) {
                // Do nothing.
            }
        }
    }

    @Override
    public String createMediator(String mediatorName) throws Exception {
        return chatFactory.getMediator("", mediatorName).getUUID();
    }

    @Override
    public void addUserToChat(String uuid, String username, RemoteClientInterface user) throws Exception {
        chatFactory.getMediator(uuid, null).addUser(username, user);
    }

    @Override
    public void messageChat(String uuid, String username, String userIP, String message) throws Exception {
        chatFactory.getMediator(uuid, null).sendMessage(username, userIP, message);
    }
}
