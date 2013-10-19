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
    private HashMap<String, Connection> connection;

    private Server(int port) {
        this.port = port;
        connection = new HashMap<String, Connection>();
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

    private void listen() {
        try {
            server = new ServerSocket(port);
            isRunning = true;
            System.out.println("Server is start!");
            System.out.println("Waiting clients...");
            while (isRunning) {
                client = server.accept();
                System.out.println("Client is connected!");
                String notLoginUser = client.toString();
                connection.put("1", new Connection(client, this));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            stop();
        }
    }

    public synchronized void changeConnection(Command cmd) {
        Connection manager = connection.get(cmd.getReceiver());
        manager.sendCommand(cmd);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isCreated() {
        return (instance != null);
    }

    private void stop()  {
        isRunning = false;
        try {
            server.close();
            client.close();
        } catch (IOException e) {
            System.out.println("Doesn't not stop server");
        }
    }


}
