package io.github.tomast13337;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

public class Server implements Runnable {
    private final Logger logger = Logger.getLogger("AppLogger");
    private String usedNames[];
    private Message messages[];

    ServerSocket serverSocket;
    private boolean running;

    public Server(int port, int messagesHistoryLength) throws IOException {
        this.messages = new Message[messagesHistoryLength];
        this.serverSocket = new ServerSocket(port);
        logger.info("Servidor iniciado na porta " + String.valueOf(port));
    }

    @Override
    public void run() {
        running = true;
        while (this.running) {
            // verificar  e adicionar novas conexões:
            // verificar e adicionar novas mensagens:
            // enviar mensagens para clientes:
        }
    }

    public void kill() throws IOException, InterruptedException {
        logger.info("Fechando servidor");
        running = false;
        serverSocket.close();
        logger.info("Servidor fechado");
    }
}
