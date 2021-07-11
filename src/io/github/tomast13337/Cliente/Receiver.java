package io.github.tomast13337.Cliente;

import java.io.BufferedReader;
import java.io.IOException;

public class Receiver extends Thread {
    private final BufferedReader in;

    public Receiver(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}