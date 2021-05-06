package model;

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

    public abstract void addUser(String username) throws Exception;
    public abstract User getUserByName(String username) throws Exception;
    public abstract void sendAffiliatedData(String username) throws Exception;
    public abstract void sendMessage(String username, String userIP, String message);
}
