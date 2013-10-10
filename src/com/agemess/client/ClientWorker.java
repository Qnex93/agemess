package com.agemess.client;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 10.10.13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class ClientWorker implements Runnable{
    private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientWorker(Socket client) {
        try {
            this.client = client;
            InputStream inStream = client.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = client.getOutputStream();
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
        while (true) {
        }
//            client.close();


    }
}
