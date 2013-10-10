package com.agemess.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 10.10.13
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */
public class Program {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        Communication client = new Communication(ip, 777);
    }
}
