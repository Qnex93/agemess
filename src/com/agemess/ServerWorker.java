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
public class ServerWorker implements Runnable {
    private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;

    public ServerWorker(Socket client) {
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
        String s = null;
        try {
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
                writer.println(s);
            }
            client.close();
        } catch (IOException e) {
            System.out.println("Error");
        }


    }
}
