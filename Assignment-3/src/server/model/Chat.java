package server.model;

import common.network.Message;
import common.network.RemoteClientInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Chat extends Mediator {

    private Map<String, RemoteClientInterface> participants;
    private ArrayList<Message> messagesHistory;

    public Chat(String uuid, String name) {
        super(uuid, name);
        participants = new HashMap<>();
        messagesHistory = new ArrayList<>();
    }

    @Override
    public void reestablishConnection(String username, RemoteClientInterface user) throws Exception {
        // If that particular user is not an actual participant of the chat throw an error.
        if (!participants.containsKey(username)) throw new Exception("This user is not part of this chat.");
        // Store the new RMI stub of the user representing the new connection.
        participants.put(username, user);
        // Send all the chat history to that particular participant.
        for (Message message : messagesHistory) user.receiveMessage(getUUID(), new Message(message.getMessage(), message.getDate(), message.getSender(), "hidden"));
    }

    @Override
    public void addUser(String username, RemoteClientInterface user) throws Exception {
        // If participant is already in the chat throw an error.
        if (participants.get(username) != null) throw new Exception("User already exists in this chat.");
        // If not add him to the hashmap storing all participants of the chat.
        participants.put(username, user);
        // Sent all the history of messages to the participant that just joined.
        for (Message message : messagesHistory) user.receiveMessage(getUUID(), new Message(message.getMessage(), message.getDate(), message.getSender(), "hidden"));
        // Send a message to all participants that a a new participant just joined.
        sendMessage(username, user.getClientIP(), username + " just joined the chat.");
    }

    @Override
    public void sendMessage(String username, String userIP, String message) {
        Date currentDate = new Date();
        messagesHistory.add(new Message(message, currentDate, username, userIP));
        // Send the message to all participants of the chat.
        for (String user : participants.keySet()) {
            try {
                participants.get(user).receiveMessage(getUUID(), new Message(message, currentDate, username, "hidden"));
            } catch (Exception e) {
                // If the message couldn't be sent to a particular participant of the chat log an error in the server console.
                System.out.println("\u001b[35mCould not send a message to " + user + " in chat uuid: " + getUUID() + " .\u001b[39m");
                System.out.println(e.getMessage());
            }
        }
        // Log the message in the server console.
        System.out.println("\u001b[35mChat:[" + super.getUUID() + "] " + username + " IP:[" + userIP + "] [" + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(currentDate) + " ]:" + message + "\u001b[39m");
    }
}
