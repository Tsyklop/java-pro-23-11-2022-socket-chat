package ua.hillel.server.messaging.handler;

import ua.hillel.server.messaging.queue.MessageQueue;
import ua.hillel.server.model.type.MessageType;
import ua.hillel.server.model.message.SimpleMessage;

public class SimpleMessageHandler extends AbstractMessageHandler<SimpleMessage> {

    public SimpleMessageHandler(MessageQueue messageQueue) {
        super(messageQueue, MessageType.MESSAGE);
    }

    @Override
    public void handle(SimpleMessage message) {
        this.messageQueue.add(message);
    }

}
