package io.github.tomast13337.Cliente;

import io.github.tomast13337.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    private final int port;
    private final String address;
    private final String name;
    private Socket connection;
    private boolean running;

    public Client(String address, int port, String name) throws IOException {
        this.address = address;
        this.port = port;
        this.name = name;

    }

    @Override
    public void run() {
        try {
            this.connection = new Socket(address, port);
            Main.logger.info("Conectando com sucesso a " + address + ":" + String.valueOf(port));
            running = true;
            PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while ((userInput = stdin.readLine()) != null) {
                out.println(userInput);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            Main.logger.info(e.getMessage());
        }
    }

    public void Kill() throws IOException {
        connection.close();
        Main.logger.info("Terminada a conexão com " + this.address + ":" + String.valueOf(this.port));
    }
}
