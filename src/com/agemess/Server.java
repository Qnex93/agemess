package com.agemess;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 01.10.13
 * Time: 12:51
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    private static Server instance;
    private boolean isRunning;
    private int port;
    private ServerSocket server;
    private Socket client;
    private HashMap<String, Connection> managers;

    private Server(int port) {
        this.port = port;
        managers = new HashMap<String, Connection>();
    }

    public static Server create(int port) {
        if (instance == null) {
            instance = new Server(port);
        }
        return instance;
    }

    public void run() throws Exception {
        if (!isRunning) {
            listen();
        }
    }

    private void listen() throws IOException {
        try {
            server = new ServerSocket(port);
            isRunning = true;
            System.out.println("Server is start!");
            System.out.println("Waiting clients...");
            while (true) {
                client = server.accept();
                System.out.println("Client is connected!");
                managers.put("user", new Connection(client, this));
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            closeConnection();
        }
    }

    public synchronized void changeThread(Command cmd) {
        Connection manager = managers.get("user");
        manager.sendCommand(cmd);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isCreated() {
        return (instance != null);
    }

    private void closeConnection() throws IOException {
        server.close();
        client.close();
    }


}
