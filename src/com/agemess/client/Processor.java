package com.agemess.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 10.10.13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class Processor implements Runnable{
    private Socket server;
    private BufferedReader reader;
    private PrintWriter writer;

    public Processor(Socket server) {
        this.server = server;
        try {
            InputStream inStream = this.server.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = this.server.getOutputStream();
            writer = new PrintWriter(outStream, true);
            Thread threadListener = new Thread(this);
            threadListener.start();
            final Processor proc = this;
            Thread sendThread = new Thread(new Runnable() {
                public void run() {
                    Scanner scanner = new Scanner(System.in);
                    while (scanner.hasNext()) {
                        proc.send(scanner.nextLine());
                    }
                }

            });
            sendThread.start();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void run() {
        receive();
    }

    private void receive(){
        String s = null;
        try {
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
            server.close();
        } catch (IOException e) {
            System.out.println("Error");
        }

    }

    private void send(String msg) {
        writer.println(msg);
    }
}
