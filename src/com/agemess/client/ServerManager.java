package com.agemess.client;

import java.io.*;
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
public class ServerManager implements Runnable {
    private Socket socket;
    private InputStream inStream;
    private PrintWriter writer;
    private BufferedReader reader;

    public ServerManager(Socket server) {
        this.socket = server;
        try {
            inStream = this.socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inStream));
            OutputStream outStream = this.socket.getOutputStream();
            writer = new PrintWriter(outStream, true);
            Thread threadListener = new Thread(this);
            threadListener.start();
            String msg = "This holiday season";
            sendMsg(msg);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private void receive() {
        try {
            PrintWriter printWriter = new PrintWriter("cmd.txt");
            while (socket.isConnected()) {
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

    private void sendCommand(Command cmd) {
        sendType(cmd);
        sendTarget(cmd);
        sendData(cmd);
    }

    private void sendType(Command cmd) {
        writer.println(cmd.getType().ordinal());
        writer.flush();
    }

    private void sendTarget(Command cmd) {
        writer.println(cmd.getTarget());
        writer.flush();
    }

    private void sendData(Command cmd) {
        writer.println(cmd.getData());
        writer.flush();
    }

    public void run() {
        receive();
    }
}
