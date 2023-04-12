package ua.hillel.server.model.message;

import ua.hillel.server.model.type.MessageType;

public class SystemMessage extends Message {

    private final String value;

    public SystemMessage(String value) {
        super(MessageType.SYSTEM);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
