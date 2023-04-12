package ua.hillel.server.messaging.handler;

import ua.hillel.server.model.message.Message;
import ua.hillel.server.model.type.MessageType;

public interface ClientMessageHandler<T extends Message> {

    void handle(T message);

    boolean isSupports(MessageType type);

}
