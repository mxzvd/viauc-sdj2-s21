package model;

import java.beans.PropertyChangeListener;

public interface Model {
    void addListenerToUser(String username, PropertyChangeListener listener);
    void sendAllAffiliatedDataTo(String username);
    void sendActiveUsersOfChatTo(String uuid, String username) throws Exception;
    String createMediator(String mediatorName) throws Exception;
    void messageChat(String uuid, String username, String userIP, String message) throws Exception;
    void addUserToChat(String uuid, String username, PropertyChangeListener listener) throws Exception;
    void removeUserFromChat(String uuid, String username, PropertyChangeListener listener) throws Exception;
}
