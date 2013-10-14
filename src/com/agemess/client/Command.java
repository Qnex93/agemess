package com.agemess.client;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 12.10.13
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public class Command implements Serializable{
    private CommandType type;
    private String target;
    private String data;
    private String senderIp;
    private String senderName;

    public Command(CommandType type, String target, String data) {
        this.type = type;
        this.target = target;
        this.data = data;
    }

    public Command(CommandType type, String target) {
        this.type = type;
        this.target = target;
        this.data = "";
    }


    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSenderIp() {
        return senderIp;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
