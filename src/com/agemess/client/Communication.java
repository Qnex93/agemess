package com.agemess.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.Socket;
import java.net.UnknownHostException;

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

    public Communication(InetAddress ip, int port) throws UnknownHostException {
        this.port = port;
        this.ip = ip;
        try {
            Socket client = new Socket(ip, port);
        } catch (IOException e) {
            System.out.println("Client doesn't connect!");
        }

    }

    public boolean isRunning(){
        return isRunning;
    }

    public boolean isCreated(){
        return (instance != null);
    }


}
