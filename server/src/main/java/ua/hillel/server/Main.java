package ua.hillel.server;

import org.apache.commons.lang3.RandomStringUtils;
import ua.hillel.server.messaging.parser.MessageParser;
import ua.hillel.server.messaging.handler.ClientMessageHandler;
import ua.hillel.server.messaging.handler.FileMessageHandler;
import ua.hillel.server.messaging.handler.SimpleMessageHandler;
import ua.hillel.server.messaging.handler.SystemMessageHandler;
import ua.hillel.server.messaging.queue.MessageQueue;
import ua.hillel.server.messaging.queue.MessageQueueHandler;
import ua.hillel.server.repository.ClientRepository;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        MessageQueue messageQueue = new MessageQueue();

        /*List<ClientMessageHandler<?>> clientMessageHandlers = new ArrayList<>();
        clientMessageHandlers.add(new FileMessageHandler(messageQueue));
        clientMessageHandlers.add(new SimpleMessageHandler(messageQueue));
        clientMessageHandlers.add(new SystemMessageHandler(messageQueue));*/

        List<ClientMessageHandler<?>> clientMessageHandlers = List.of(
                new FileMessageHandler(messageQueue),
                new SimpleMessageHandler(messageQueue),
                new SystemMessageHandler(messageQueue)
        );

        MessageParser messageParser = new MessageParser();

        ClientRepository clientRepository = new ClientRepository();

        MessageQueueHandler messageQueueHandler = new MessageQueueHandler(messageQueue, clientRepository);

        Thread messageQueueHandlerThread = new Thread(messageQueueHandler);
        messageQueueHandlerThread.setDaemon(true);
        messageQueueHandlerThread.start();

        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            while (true) {

                Socket socket = serverSocket.accept();

                String clientName = RandomStringUtils.randomAlphanumeric(10);

                System.out.println("Client " + clientName + " connected");

                ClientThread client = new ClientThread(clientName, socket, messageParser, clientMessageHandlers);

                Thread clientThread = new Thread(client);
                clientThread.setDaemon(true);
                clientThread.start();

                clientRepository.add(client);

            }

        }

    }

}