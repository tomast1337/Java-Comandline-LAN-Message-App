package io.github.tomast13337.Server;

import io.github.tomast13337.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {

    PrintWriter out;
    BufferedReader in;
    Socket connection;
    String name;

    public ServerThread(Socket connection) throws IOException {
        this.connection = connection;
        this.in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        this.out = new PrintWriter(connection.getOutputStream(), true);
    }

    @Override
    public void run() {
        String inputLine, outputLine;
        try {
            while ((inputLine = in.readLine()) != null) {
                outputLine = inputLine;
                if (outputLine.equalsIgnoreCase("/sair"))
                    Main.logger.info("Cliente desconectado.");
                break;
            }
        } catch (IOException e) {
            Main.logger.info(e.getMessage());
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
