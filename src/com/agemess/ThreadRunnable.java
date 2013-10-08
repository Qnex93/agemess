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
public class ThreadRunnable implements Runnable {
    private Socket client;

    public ThreadRunnable(Socket client) {
        this.client = client;
        Thread threadListener = new Thread(this);
        threadListener.start();
    }

    public void run() {
        try {
            InputStream inStream = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = client.getOutputStream();
            PrintWriter writer = new PrintWriter(outStream, true);
            String s = null;
            while ((s = reader.readLine()) != null){
                System.out.println(s);
                writer.println(s);
            }
            Communication.numberOfOnline--;
            client.close();
            System.out.println("Now, " + Communication.numberOfOnline + " clients is online");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
