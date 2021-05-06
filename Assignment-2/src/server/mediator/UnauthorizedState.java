package mediator;

import model.Model;

public class UnauthorizedState extends HandlerState {

    public UnauthorizedState(Model model) {
        super(model);
    }

    @Override
    public boolean register(ClientHandler clientHandler, String username, String password) throws Exception {
        AuthorizedState.getInstance(super.getModel(), username, password);
        return true;
    }

    @Override
    public void login(ClientHandler clientHandler, String username, String password) throws Exception {
        clientHandler.setState(AuthorizedState.getInstance(null, username, password));
        super.getModel().addListenerToUser(username, clientHandler);
        super.getModel().sendAllAffiliatedDataTo(username);
    }

    @Override
    public void requestActiveUsersOfChat(String uuid) throws Exception {
        throw new Exception("Log in to perform this request.");
    }

    @Override
    public void createChat(ClientHandler clientHandler, String chatName) throws Exception {
        throw new Exception("Log in to perform this request.");
    }

    @Override
    public void messageChat(String uuid, String message, String userIP) throws Exception {
        throw new Exception("Log in to perform this request.");
    }

    @Override
    public void joinChat(String uuid, ClientHandler clientHandler) throws Exception {
        throw new Exception("Log in to perform this request.");
    }
}
