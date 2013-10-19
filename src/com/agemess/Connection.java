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
public class Connection implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private User user;
    private Server server;

    public Connection(Socket client, Server server) {
        this.socket = client;
        this.server = server;
        try {
            InputStream inStream = this.socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = this.socket.getOutputStream();
            writer = new PrintWriter(outStream, true);
            Thread threadListener = new Thread(this);
            threadListener.start();
        } catch (IOException e) {
            System.out.println("Connecion is not established");
        }
    }

    private void receive() {
        try {
            while (socket.isConnected()) {
                CommandType type = getType();
                String receiver = getReceiver();
                String sender = getSender();
                String data = getData();
                System.out.println(socket.toString());
                if ((type != null) && (receiver != null) && (sender != null)) {
                    Command cmd = new Command(type, receiver, sender);
                    cmd.setData(data);
                    commandProcessing(cmd);
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {}
    }

    private void login(Command cmd) {
        user = new User();
    }

    private boolean isLogin() {
        return true;
    }

    public void sendCommand(Command cmd) {
        sendObj(cmd.getType().ordinal());
        sendObj(cmd.getReceiver());
        sendObj(cmd.getSender());
        sendObj(cmd.getData());
    }


    private void sendObj(Object item) {
        writer.println(item);
        writer.flush();
    }

    private void commandProcessing(Command cmd) {
        switch (cmd.getType()) {
            case UserLogin:
                login(cmd);
            case Message:
                if (isLogin())
                    server.changeConnection(cmd);
            case UserExit:
                close();

        }
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

    private String getReceiver() {
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

    private String getSender() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private void close() {
        try {
            if (socket.isConnected()) {
                socket.shutdownOutput();
                socket.shutdownInput();
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Doesn't not close connection");
        }
    }

    public void run() {
        receive();
    }

}
