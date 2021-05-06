package model;

public class ChatFactory extends MediatorFactory {

    @Override
    protected Mediator createMediator(String mediatorName) {
        return new Chat(java.util.UUID.randomUUID().toString(), mediatorName);
    }
}
