package com.agemess;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 07.10.13
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
public class ClientManager implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private User user;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private DataInputStream dataInputStream;
    private Server server;

    public ClientManager(Socket client, Server server) {
        this.socket = client;
        this.server = server;
        try {
            InputStream inStream = this.socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = this.socket.getOutputStream();
            writer = new PrintWriter(outStream, true);
            inputStream = new ObjectInputStream(socket.getInputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            Thread threadListener = new Thread(this);
            threadListener.start();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private void receive() {
        while (socket.isConnected()) {
            CommandType type = getType();
            String target = getTarget();
            String data = getData();
            if ((type != null) && (target != null) && (data != null)) {
                Command cmd = new Command(type, target, data);
                server.changeThread(cmd);
            }
        }
    }


    public void sendCommand(Command cmd) {
        sendObj(cmd.getType().ordinal());
        sendObj(cmd.getTarget());
        sendObj(cmd.getData());
    }

    private void sendObj(Object item) {
        writer.println(item);
        writer.flush();
    }


    private CommandType getType() {
        try {
            String test = reader.readLine();
            int valueOfType = Integer.parseInt(test);
            return CommandType.fromValue(valueOfType);
        } catch (IOException e) {
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String getTarget() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String getData() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }


    public void run() {
        receive();
    }

}
