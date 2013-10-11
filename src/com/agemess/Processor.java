package com.agemess;

import com.agemess.Communication;

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
    private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;

    public Processor(Socket client) {
        this.client = client;
        try {
            InputStream inStream = this.client.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = this.client.getOutputStream();
            writer = new PrintWriter(outStream, true);
            Thread threadListener = new Thread(this);
            threadListener.start();
            try {
                this.send();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
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
            client.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private void send() throws InterruptedException {
        while (client.isConnected()) {
            Thread.sleep(1000);
            writer.println("hello");
        }
    }
}
