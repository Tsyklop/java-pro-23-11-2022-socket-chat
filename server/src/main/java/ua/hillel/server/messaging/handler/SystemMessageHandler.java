package ua.hillel.server.messaging.handler;

import ua.hillel.server.messaging.queue.MessageQueue;
import ua.hillel.server.model.type.MessageType;
import ua.hillel.server.model.message.SystemMessage;

public class SystemMessageHandler extends AbstractMessageHandler<SystemMessage> {

    public SystemMessageHandler(MessageQueue messageQueue) {
        super(messageQueue, MessageType.SYSTEM);
    }

    @Override
    public void handle(SystemMessage message) {

    }

}
