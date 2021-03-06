package com.agemess;

/**
 * Created with IntelliJ IDEA.
 * User: Qnex
 * Date: 10.10.13
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
public enum CommandType {
    UserLogin,
    UserExit,
    Message,
    ChangeStatus;

    public static CommandType fromValue(int value) {
        for (CommandType item : CommandType.values()) {
            if (item.ordinal() == value) {
                return item;
            }
        }
        return null;
    }
}
