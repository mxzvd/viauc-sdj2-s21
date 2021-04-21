package server.mediator;

public class UserPackage {
    private String user;
    private String password;

    public UserPackage(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserPackage: " + user + ", " + password;
    }
}
