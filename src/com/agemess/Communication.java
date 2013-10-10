package com.agemess;

import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 01.10.13
 * Time: 12:51
 * To change this template use File | Settings | File Templates.
 */
public class Communication {
    private static Communication instance;
    private boolean isRunning;
    private int port;
    private ServerSocket server;
    private Socket client;
    static int numberOfOnline = 0;

    private Communication(int port) {
        this.port = port;
    }

    public static Communication create(int port) {
        if (instance == null){
            instance = new Communication(port);
        }
        return instance;
    }

    public void run() throws Exception {
        try{
            if (!isRunning) {
                server = new ServerSocket(port);
                System.out.println("Waiting clients...");
                while (true){
                    client = server.accept();
                    System.out.println("Client is connected!");
                    numberOfOnline++;
                    new Worker(client);
                    System.out.println("Now, " + numberOfOnline + " clients is online");
                }
            }

        } catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            server.close();
            client.close();
        }

    }

    public boolean isRunning(){
        return isRunning;
    }

    public boolean isCreated(){
        return (instance != null);
    }



}
