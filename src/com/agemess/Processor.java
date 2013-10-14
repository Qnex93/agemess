package com.agemess;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 07.10.13
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
public class Processor implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private User user;

    public Processor(Socket client) {
        this.socket = client;
        try {
            InputStream inStream = this.socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = this.socket.getOutputStream();
            writer = new PrintWriter(outStream, true);
            Thread threadListener = new Thread(this);
            threadListener.start();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void run() {
        receive();
    }

    private void receive(){
        while (socket.isConnected()){

        }
    }

    private void send() {

    }

    private boolean login(String userId, String pass) {
        return true;
    }

}
