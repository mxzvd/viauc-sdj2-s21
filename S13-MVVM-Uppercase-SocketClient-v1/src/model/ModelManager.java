package model;

import mediator.ServerModel;
import mediator.UppercaseClient;
import java.io.IOException;

public class ModelManager implements Model {

    private ServerModel serverModel;
    public ModelManager() throws IOException {
        serverModel = new UppercaseClient();
        serverModel.connect();
    }

    @Override public synchronized String convert(String source) throws IOException {
        return serverModel.convert(source);
    }
}
