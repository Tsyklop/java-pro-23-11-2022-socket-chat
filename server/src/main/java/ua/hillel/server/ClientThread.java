package ua.hillel.server;

import ua.hillel.server.messaging.parser.MessageParser;
import ua.hillel.server.messaging.handler.ClientMessageHandler;
import ua.hillel.server.model.message.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

public class ClientThread implements Runnable {

    private final String name;

    private final Socket socket;

    private final MessageParser messageParser;

    private final DataOutputStream outputStream;

    private final List<ClientMessageHandler<?>> clientMessageHandlers;

    public ClientThread(String name,
                        Socket socket,
                        MessageParser messageParser,
                        List<ClientMessageHandler<?>> clientMessageHandlers) throws IOException {
        this.name = name;
        this.socket = socket;
        this.messageParser = messageParser;
        this.outputStream = new DataOutputStream(socket.getOutputStream());
        this.clientMessageHandlers = clientMessageHandlers;
    }

    public String getName() {
        return this.name;
    }

    public void sendMessage(String message) throws IOException {
        this.outputStream.writeUTF(message);
        this.outputStream.flush();
    }

    @Override
    public void run() {

        try {

            DataInputStream is = new DataInputStream(socket.getInputStream());

            while (true) {

                String clientMessage = is.readUTF();

                try {

                    Message message = this.messageParser.parse(clientMessage);

                    for (ClientMessageHandler clientMessageHandler : this.clientMessageHandlers) {
                        if (clientMessageHandler.isSupports(message.getType())) {
                            clientMessageHandler.handle(message);
                        }
                    }

                } catch (Exception e) {
                    // send client info about error
                }

                //this.messageQueue.add(message);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientThread that = (ClientThread) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
