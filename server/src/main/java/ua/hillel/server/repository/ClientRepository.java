package ua.hillel.server.repository;

import ua.hillel.server.ClientThread;

import java.util.HashSet;
import java.util.Set;

public class ClientRepository {

    private final Set<ClientThread> connectedClients = new HashSet<>();

    public int size() {
        return this.connectedClients.size();
    }

    public Set<ClientThread> getConnectedClients() {
        return new HashSet<>(this.connectedClients);
    }

    public void add(ClientThread clientThread) {
        if (this.connectedClients.contains(clientThread)) {
            return;
        }
        this.connectedClients.add(clientThread);
    }

    public boolean delete(String clientName) {
        return this.connectedClients.removeIf(clientThread -> clientThread.getName().equals(clientName));
    }

}
