package client.model;

import client.mediator.UserClient;
import java.io.IOException;

public class ModelManager implements Model {

    private UserClient client;

    public ModelManager() throws IOException {
        client = new UserClient();
    }

    @Override public void login(String userName, String password) throws Exception {
        client.login(userName, password);
    }
}
