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
            sendMsg(msg);
            System.out.println("Connected to server.");
        } catch (IOException e) {
            System.out.println("Error! Doesn't connect.");
        }
    }

    private void receive() {
        try {
            PrintWriter printWriter = new PrintWriter("cmd.txt");
            while (server.isConnected()) {
                CommandType type = getType();
                String target = getTarget();
                String data = getData();
                printWriter.println(type + " " + target + " " + data );
                printWriter.flush();
            }
        } catch (FileNotFoundException e) {}


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

    public void sendMsg(String msg) {
        Command cmd = new Command(CommandType.Message, "Alex", msg);
        sendCommand(cmd);

    }

    public void sendCommand(Command cmd) {
        sendObject(cmd.getType().ordinal());
        sendObject(cmd.getTarget());
        sendObject(cmd.getData());
    }

    private void sendObject(Object item) {
        writer.println(item);
        writer.flush();
    }
    public void run() {
        receive();
    }
}
