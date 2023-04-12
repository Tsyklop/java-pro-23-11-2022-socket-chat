package ua.hillel.server.model.message;

import ua.hillel.server.model.type.MessageType;

public abstract class Message {

    private final MessageType type;

    public Message(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

}
