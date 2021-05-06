package client.model;

public interface Model {
    void register(String username, String password) throws Exception;
    void login(String username, String password) throws Exception;
    void createChat(String chatName) throws Exception;
    void joinChat(String uuid) throws Exception;
    void sendMessage(String uuid, String message) throws Exception;
}
