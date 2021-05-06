package server.model;

import common.network.RemoteClientInterface;

public abstract class Mediator {

    private String uuid;
    private String name;

    public Mediator(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public abstract void reestablishConnection(String username, RemoteClientInterface user) throws Exception;
    public abstract void addUser(String username, RemoteClientInterface user) throws Exception;
    public abstract void sendMessage(String username, String userIP, String message);
}
