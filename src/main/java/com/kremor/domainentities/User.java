package com.kremor.domainentities;


public class User {

    private String username;
    private String password;

    public User() {
        this("admin@gmail.com", "adminpass");
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public final String getUsername() {
        return username;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public final String getPassword() {
        return password;
    }
}
