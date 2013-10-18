package com.agemess;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 01.10.13
 * Time: 0:08
 * To change this template use File | Settings | File Templates.
 */
public class Program {
    private static int port;
    private static final int defaultPort = 777;

    public static void main(String[] args) throws Exception {
        port = defaultPort;
        try {
            if (args.length != 0) {
                port = Integer.parseInt(args[0]);
            }
        } catch (NumberFormatException e) {
            System.out.println("The port number is set default, because impossible read the value.");
        }
        Server server = Server.create(port);
        server.run();
    }
}
