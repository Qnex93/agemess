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

    public static void main(String[] args) throws Exception {
        try {
            if (args.length == 0) {
                port = 777;
            } else {
                port = Integer.parseInt(args[1]);
            }
        } catch (NumberFormatException e) {
            port = 777;
            System.out.println("The port number is set default, because impossible read the value.");
        }
        Communication server = Communication.create(port);
        server.run();
    }
}
