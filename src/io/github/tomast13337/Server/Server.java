package io.github.tomast13337.Server;

import io.github.tomast13337.Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private boolean running;
    private ArrayList clientsThreads;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        Main.logger.info("Servidor iniciado na porta " + String.valueOf(port));
        this.clientsThreads = new ArrayList<ServerThread>();
        while (this.running) {

        }
    }

    @Override
    public void run() {
        try {
            while (this.running) {
                Main.logger.info("Servidor esperando nova conexão com clientes...");
                Socket newClientSocket = serverSocket.accept();
                Main.logger.info("Nova conexão feita");
                ServerThread newThread = new ServerThread(newClientSocket);
                newThread.start();
                clientsThreads.add(newThread);
            }
        } catch (IOException e) {
            Main.logger.info(e.getMessage());
        }
    }

    public void SendToClients() {

    }

    public void kill() throws IOException {
        running = false;
        serverSocket.close();
        Main.logger.info("Servidor fechado");
    }
}
