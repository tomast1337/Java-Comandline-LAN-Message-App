package io.github.tomast13337;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class Client implements Runnable {
    private final int port;
    private final String address;
    private final Logger logger = Logger.getLogger("AppLogger");
    private final String name;
    private Socket socket;
    private Message messages[];
    private boolean running;

    public Client(String address, int port, String name, int messagesHistoryLength) throws IOException {
        this.messages = new Message[messagesHistoryLength];
        this.socket = new Socket(address, port);

        this.address = address;
        this.port = port;
        this.name = name;
        logger.info("Conectando-se a " + address + ":" + String.valueOf(port));
    }

    @Override
    public void run() {
        running = true;
        while (this.running) {

        }
    }

    public void Kill() throws IOException {
        socket.close();
        logger.info("Terminada a conexão com " + this.address + ":" + String.valueOf(this.port));
    }
}
