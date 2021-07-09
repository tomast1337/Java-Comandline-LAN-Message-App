package io.github.tomast13337;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Server server;
    private static Client client;

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("AppLogger");
        logger.setLevel(Level.INFO);
        if (args.length < 1) {
            logger.info("Numero de argumentos insuficiente");
            logger.info("O Primeiro argumento deve ser -host ou -cliente");
        } else {
            Main main = new Main();
            if (args[0].equalsIgnoreCase("cliente")) {
                logger.info("Iniciando no modo cliente");
                try {
                    main.runClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (args[0].equalsIgnoreCase("cliente/host") || args[0].equalsIgnoreCase("host/cliente") || args[0].equalsIgnoreCase("host")) {
                logger.info("Iniciando no modo cliente/host");
                try {
                    main.runServer();
                    main.runClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                logger.info("Argumento \"" + args[0] + "\" invalido");
            }
        }
    }

    void runServer() throws IOException {
        server = new Server(1234, 500);
    }

    void runClient() throws IOException {
        client = new Client("", 1234, "", 500);
    }
}
