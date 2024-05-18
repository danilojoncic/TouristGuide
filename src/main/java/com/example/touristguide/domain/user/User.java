package com.example.touristguide.domain.user;

public class User {
    private int user_id;
    private String email;
    private String username;
    private Tip tip;
    private Status status;
    private String password;

    public User(int user_id, String email, String username, Tip tip, Status status, String password) {
        this.user_id = user_id;
        this.email = email;
        this.username = username;
        this.tip = tip;
        this.status = status;
        this.password = password;
    }

    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
