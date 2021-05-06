package server.model;

import common.network.RemoteClientInterface;

public interface Model {
    void reestablishConnection(String username, RemoteClientInterface user);
    String createMediator(String mediatorName) throws Exception;
    void addUserToChat(String uuid, String username, RemoteClientInterface user) throws Exception;
    void messageChat(String uuid, String username, String userIP, String message) throws Exception;
}
