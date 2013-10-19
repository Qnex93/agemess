package com.agemess.client;

import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 10.10.13
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */
public class Program {
    static InetAddress ip;
    static int port;
    static final int defaultPort = 777;

    public static void main(String[] args) {
        try {
            port = defaultPort;
            if (args.length == 0) {
                ip = InetAddress.getByName("127.0.0.1");
            }
            if (args.length == 1) {
                ip = InetAddress.getByName(args[0]);
            }
            if (args.length == 2) {
                ip = InetAddress.getByName(args[0]);
                port = Integer.parseInt(args[1]);
            }
            Client client = new Client(ip, port);
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
