package model;

import java.util.ArrayList;

public interface Colleague {
    void receiveAffiliatedData(ArrayList<Message> data);
    void receiveMessage(Message message);
}
