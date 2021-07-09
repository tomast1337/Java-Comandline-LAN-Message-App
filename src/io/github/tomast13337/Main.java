package io.github.tomast13337;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("AppLogger");
        logger.setLevel(Level.INFO);
        if (args.length < 1) {
            logger.info("Numero de argumentos insuficiente");
            System.out.println("O Primeiro argumento deve ser -host ou -cliente");
        } else {
            if (args[0].equalsIgnoreCase("cliente")) {
                logger.info("Iniciando no modo cliente");
            } else if (args[0].equalsIgnoreCase("cliente/host") || args[0].equalsIgnoreCase("host/cliente") || args[0].equalsIgnoreCase("host")) {
                logger.info("Iniciando no modo cliente/host");
            } else {
                logger.info("Argumento \"" + args[0] + "\" invalido");
            }
        }
        // write your code here
    }
}
