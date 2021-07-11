package io.github.tomast13337.Server;

import io.github.tomast13337.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ServerThread extends Thread {

    private final PrintWriter out;
    private final BufferedReader in;
    private final Socket connection;
    private String nome = "";
    private ArrayList<ServerThread> clients;

    public ServerThread(Socket connection, ArrayList clients) throws IOException {
        this.clients = clients;
        this.connection = connection;
        this.in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        this.out = new PrintWriter(connection.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equalsIgnoreCase("/sair"))
                    break;

                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(new Date());
                StringBuilder message = new StringBuilder();
                message.append("[");
                message.append(calendar.get(Calendar.HOUR_OF_DAY));
                message.append(":");
                message.append(calendar.get(Calendar.MINUTE));
                message.append("][");
                message.append(nome);
                message.append("] ");
                message.append(inputLine);
                SendToClients(message.toString());
                Main.logger.info(message.toString());
            }
        } catch (IOException e) {
            Main.logger.info(e.getMessage());
        }
    }

    private void SendToClients(String content) {
        for (ServerThread client : clients) {
            client.getOut().println(content);
        }
    }

    public PrintWriter getOut() {
        return this.out;
    }

    public String getNome() {
        return this.nome;
    }

    public BufferedReader getIn() {
        return this.in;
    }
}
