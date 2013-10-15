package com.agemess.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 10.10.13
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */
public class Communication {
    private int port;
    private InetAddress ip;
    private static Communication instance;
    private boolean isRunning;
    private Socket server;

    public static Communication create(InetAddress ip, int port) {
        if (instance == null){
            instance = new Communication(ip, port);
        }
        return instance;
    }

    private Communication(InetAddress ip, int port) {
        this.port = port;
        this.ip = ip;
    }

    public void run() {
        try {
            Socket server = new Socket(ip, port);
            new Client(server);
            System.out.println("Connected to server.");
            isRunning = true;
        } catch (IOException e) {
            System.out.println("Error! Doesn't connect.");
        }
    }

    public boolean isRunning(){
        return isRunning;
    }

    public boolean isCreated(){
        return (instance != null);
    }


}
