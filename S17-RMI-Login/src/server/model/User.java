package server.model;

public class User {

    private UserName userName;
    private Password password;
    private MyDate registrationDate;

    public User(UserName userName, Password password) {
        if (userName == null || password == null) {
            throw new IllegalArgumentException("Null username or password");
        }
        this.userName = userName;
        this.password = password;
        this.registrationDate = new MyDate();
    }

    public String toString() {
        return userName + ", password = " + password + ", " + registrationDate;
    }

    public UserName getUserName() {
        return userName;
    }

    public Password getPassword() {
        return password;
    }

    public MyDate getRegistrationDate() {
        return registrationDate;
    }
}
