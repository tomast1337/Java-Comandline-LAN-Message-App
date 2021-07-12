package io.github.tomast13337.Server;

import io.github.tomast13337.Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
    private final ServerSocket serverSocket;
    static ArrayList<ServerThread> clients = new ArrayList<>();
    private boolean running;


    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        running = false;
        Main.logger.info("Servidor iniciado na porta " + port);
    }

    @Override
    public void run() {
        running = true;
        try {
            while (this.running) {
                Main.logger.info("Servidor pronto para fazer uma nova conexão com clientes...");
                Socket newConnection = serverSocket.accept();
                ServerThread newClient = new ServerThread(newConnection);
                newClient.start();
                clients.add(newClient);
                Main.logger.info("Nova conexão feita");
            }
        } catch (IOException e) {
            Main.logger.info(e.getMessage());
        }
    }

    public void kill() throws IOException {
        running = false;
        serverSocket.close();
        Main.logger.info("Servidor fechado");
    }
}
