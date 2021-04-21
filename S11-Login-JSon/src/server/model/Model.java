package server.model;

public interface Model {
    public int getNumberOfUsers();
    public User getUser(int index) throws IndexOutOfBoundsException;
    public User getUserByName(String name);
    public void addUser(User user) throws IllegalStateException, IllegalArgumentException;
    public void addUser(UserName userName, Password password) throws IllegalStateException, IllegalArgumentException;
    public void addUser(String userName, String password) throws IllegalStateException, IllegalArgumentException;
    public boolean contains(User user);
}
