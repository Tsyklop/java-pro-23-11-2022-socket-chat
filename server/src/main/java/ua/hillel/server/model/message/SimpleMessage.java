package ua.hillel.server.model.message;

import ua.hillel.server.model.type.MessageType;

public class SimpleMessage extends Message {

    private final String value;

    public SimpleMessage(String value) {
        super(MessageType.MESSAGE);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
