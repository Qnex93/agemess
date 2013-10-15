package com.agemess.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import com.agemess.Command;
import com.agemess.CommandType;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 10.10.13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class Client implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ObjectOutputStream outputStream;


    public Client(Socket server) {
        this.socket = server;
        try {
            InputStream inStream = this.socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = this.socket.getOutputStream();
            writer = new PrintWriter(outStream, true);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            Thread threadListener = new Thread(this);
            threadListener.start();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void run() {
        receive();
    }

    private void receive() {
        Command cmd = new Command(CommandType.UserLogin, "Alex");

    }

    private void send(String msg) {
        writer.println(msg);
    }
}
