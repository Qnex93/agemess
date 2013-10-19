package com.agemess;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 12.10.13
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public class Command implements Serializable{
    private CommandType type;
    private String receiver;
    private Object data;
    private String sender;

    public Command(CommandType type, String receiver, String sender) {
        this.type = type;
        this.receiver = receiver;
        this.sender = sender;
    }

    public Command(CommandType type, String receiver) {
        this.type = type;
        this.receiver = receiver;
        this.data = "";
    }


    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
