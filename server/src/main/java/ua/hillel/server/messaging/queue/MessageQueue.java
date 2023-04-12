package ua.hillel.server.messaging.queue;

import ua.hillel.server.model.message.Message;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MessageQueue {

    private final Deque<Message> messageQueue = new LinkedList<>();

    public synchronized void add(Message message) {
        this.messageQueue.add(message);
        notifyAll();
    }

    public synchronized void add(List<Message> messages) {
        this.messageQueue.addAll(messages);
        notifyAll();
    }

    public synchronized Optional<Message> getOrWait() throws InterruptedException {
        while (this.messageQueue.isEmpty()) {
            wait();
        }
        return Optional.ofNullable(this.messageQueue.poll());
    }

}
