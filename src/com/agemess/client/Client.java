package com.agemess.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import com.agemess.Command;
import com.agemess.CommandType;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 10.10.13
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class Client implements Runnable {
    private int port;
    private InetAddress ip;
    private InputStream inStream;
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket server;


    public Client(InetAddress ip, int port) {
        this.port = port;
        this.ip = ip;
    }

    public void connect() {
        try {
            server = new Socket(ip, port);
            inStream = server.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = server.getOutputStream();
            writer = new PrintWriter(outStream, true);
            Thread threadListener = new Thread(this);
            threadListener.start();
            String msg = "This holiday season";
            sendMsg(msg, "1");
            System.out.println("Connected to server.");
        } catch (IOException e) {
            System.out.println("Error! Doesn't connect.");
        }
    }

    private void receive() {
        try {
            while (server.isConnected()) {
                CommandType type = getType();
                String receiver = getReceiver();
                String sender = getSender();
                String data = getData();
                if ((type != null) && (receiver != null) && (sender != null)) {
                    Command cmd = new Command(type, receiver, sender);
                    cmd.setData(data);
                    commandProcessing(cmd);
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {}


    }

    private void commandProcessing(Command cmd) {
        switch (cmd.getType()) {
            case Message:
                showMessage(cmd.getReceiver(), cmd.getData());
        }
    }

    private void showMessage(String receiver, Object data) {
        System.out.println("***received a message***");
        System.out.println("from: " + receiver);
        System.out.println(data);
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

    private String getSender() {
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

    public void sendMsg(String msg, String user) {
        Command cmd = new Command(CommandType.Message, user, "Alex");
        cmd.setData(msg);
        sendCommand(cmd);

    }

    public void sendCommand(Command cmd) {
        sendObject(cmd.getType().ordinal());
        sendObject(cmd.getReceiver());
        sendObject(cmd.getSender());
        sendObject(cmd.getData());
    }

    private void sendObject(Object item) {
        writer.println(item);
        writer.flush();
    }

    public void run() {
        receive();
    }

    public void stop() {
        try {
            server.close();
        } catch (IOException e) {
            System.out.println("Doesn't stop client");
        }
    }
}
