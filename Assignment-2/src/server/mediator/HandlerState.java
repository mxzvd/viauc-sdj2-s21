package mediator;

import model.Model;

public abstract class HandlerState {

    private Model model;

    public HandlerState(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public abstract boolean register(ClientHandler clientHandler, String username, String password) throws Exception;
    public abstract void login(ClientHandler clientHandler, String username, String password) throws Exception;
    public abstract void requestActiveUsersOfChat(String uuid) throws Exception;
    public abstract void createChat(ClientHandler clientHandler, String chatName) throws Exception;
    public abstract void messageChat(String uuid, String message, String userIP) throws Exception;
    public abstract void joinChat(String uuid, ClientHandler clientHandler) throws Exception;
}
