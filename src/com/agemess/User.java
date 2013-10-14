package com.agemess;

public class User {
    private int userId;

    public enum Status{
        offline,
        busy,
        online
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
