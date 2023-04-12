package ua.hillel.server.messaging.handler;

import ua.hillel.server.messaging.queue.MessageQueue;
import ua.hillel.server.model.message.Message;
import ua.hillel.server.model.type.MessageType;

public abstract class AbstractMessageHandler<T extends Message> implements ClientMessageHandler<T> {

    private final MessageType supportedType;

    protected final MessageQueue messageQueue;

    protected AbstractMessageHandler(MessageQueue messageQueue, MessageType supportedType) {
        this.messageQueue = messageQueue;
        this.supportedType = supportedType;
    }

    @Override
    public boolean isSupports(MessageType type) {
        return this.supportedType == type;
    }

}
