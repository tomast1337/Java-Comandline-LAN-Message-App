package io.github.tomast13337.Server;

import io.github.tomast13337.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ServerThread extends Thread {

    private final PrintWriter out;
    private final BufferedReader in;
    private final Socket connection;
    private String nome = "";

    public ServerThread(Socket connection) throws IOException {
        this.connection = connection;
        this.in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        this.out = new PrintWriter(connection.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            nome = in.readLine();
            Main.logger.info(nome + " entrou no chat");
            while (true) {
                if (!in.ready()) continue;
                String inputLine = in.readLine();
                if (inputLine.equalsIgnoreCase("/sair"))
                    break;
                if (!inputLine.isEmpty()) {
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
            }
        } catch (IOException e) {
            Main.logger.info(e.getMessage());
        }
    }

    private void SendToClients(String content) {
        System.out.println(Server.clients.toString());
        for (ServerThread client : Server.clients) {
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
