package com.agemess;

import java.io.IOException;
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
        if (!isRunning) {
            listen();
        }
    }

    private void listen() throws IOException {
        try{
            server = new ServerSocket(port);
            isRunning = true;
            System.out.println("Server is start!");
            System.out.println("Waiting clients...");
            while (true) {
                client = server.accept();
                new Processor(client);
                System.out.println("Client is connected!");
            }

        } catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            closeConnection();
        }
    }

    public boolean isRunning(){
        return isRunning;
    }

    public boolean isCreated(){
        return (instance != null);
    }

    private void closeConnection() throws IOException {
        server.close();
        client.close();
    }




}
