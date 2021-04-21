package server.model;

public class ModelManager implements Model {

    private UserList users;

    public ModelManager() {
        this.users = new UserList();
    }

    @Override
    public int getNumberOfUsers() {
        return users.size();
    }

    @Override
    public User getUser(int index) throws IndexOutOfBoundsException {
        return users.getUser(index);
    }

    @Override
    public User getUserByName(String name) {
        return users.getUserByName(name);
    }

    @Override
    public void addUser(User user) throws IllegalStateException, IllegalArgumentException {
        users.addUser(user);
        System.out.println("ADDED: " + user);
    }

    @Override
    public void addUser(String userName, String password) throws IllegalStateException, IllegalArgumentException {
        addUser(new UserName(userName), new Password(password));
    }

    @Override
    public void addUser(UserName userName, Password password) throws IllegalStateException, IllegalArgumentException {
        users.addUser(userName, password);
        System.out.println("ADDED: " + new User(userName, password));
    }

    @Override
    public boolean contains(User user) {
        return users.contains(user);
    }
}
