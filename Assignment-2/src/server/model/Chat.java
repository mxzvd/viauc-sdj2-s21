package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chat extends Mediator {

    private Map<String, User> participants;
    private ArrayList<Message> messagesHistory;

    public Chat(String uuid, String name) {
        super(uuid, name);
        participants = new HashMap<>();
        messagesHistory = new ArrayList<>();
    }

    @Override
    public void addUser(String username) throws Exception {
        if (participants.get(username) != null) throw new Exception("User already exists in this chat.");
        participants.put(username, new User(this));
    }

    @Override
    public User getUserByName(String username) throws Exception {
        User toReturn = participants.get(username);
        if (toReturn == null) throw new Exception("No such user found.");
        return toReturn;
    }

    @Override
    public void sendAffiliatedData(String username) throws Exception {
        getUserByName(username).receiveAffiliatedData(messagesHistory);
    }

    @Override
    public void sendMessage(String username, String userIP, String message) {
        Message toSend = new Message(message, username, userIP);
        messagesHistory.add(toSend);
        for (String user : participants.keySet()) participants.get(user).receiveMessage(toSend);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("\u001b[35mChat:[" + super.getUUID() + "] " + username + " IP:[" + userIP + "] [" + sdf.format(toSend.getDate()) + " ]:" + message + "\u001b[39m");
    }
}
