package com.agemess.client;

public class User {
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public enum Status{
        offline,
        busy,
        online
    }
}
