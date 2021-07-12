package io.github.tomast13337;

import io.github.tomast13337.Cliente.Client;
import io.github.tomast13337.Server.Server;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    static public Logger logger;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        logger = Logger.getLogger("AppLogger");
        logger.setLevel(Level.INFO);
    }

    private static Thread server;
    private static Client client;

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        if (args.length < 1) {
            logger.info("Numero de parâmetros insuficiente");
            return;
        }
        try {
            switch (args[0].toLowerCase()) {
                case "client":
                    client();
                    break;
                case "server":
                    server();
                    break;
                case "server/client":
                case "client/server":
                    serverClient();
                    break;
                default:
                    logger.info("O argumento " + args[0] + " é invalido");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void server() throws IOException {
        server = new Thread(new Server(1235));
        server.start();
    }

    static void client() throws IOException {
        logger.setLevel(Level.OFF);
        System.out.println("Digite o ip do servidor");
        String address = input.nextLine();
        System.out.println("Digite seu nome");
        String name = input.nextLine();
        client = new Client(address, 1235, name);
        client.start();
        logger.info("Iniciado cliente");
    }

    static void serverClient() throws IOException {
        server = new Thread(new Server(1235));
        server.start();
        logger.setLevel(Level.OFF);
        System.out.println("Digite seu nome");
        String name = input.nextLine();
        client = new Client("localhost", 1235, name);
        client.start();
        logger.info("Iniciado cliente");
    }
}
