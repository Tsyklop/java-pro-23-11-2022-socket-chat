package ua.hillel.server.messaging.queue;

import ua.hillel.server.ClientThread;
import ua.hillel.server.model.message.Message;
import ua.hillel.server.repository.ClientRepository;

import java.util.Optional;

public class MessageQueueHandler implements Runnable {

    private final MessageQueue messageQueue;

    private final ClientRepository clientRepository;

    public MessageQueueHandler(MessageQueue messageQueue, ClientRepository clientRepository) {
        this.messageQueue = messageQueue;
        this.clientRepository = clientRepository;
    }

    @Override
    public void run() {

        while (true) {

            try {

                Optional<Message> message = this.messageQueue.getOrWait();

                if (message.isEmpty()) {
                    continue;
                }

                for (ClientThread clientThread : this.clientRepository.getConnectedClients()) {
                    try {
                        clientThread.sendMessage(message.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
