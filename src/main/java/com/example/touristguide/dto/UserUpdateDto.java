package com.example.touristguide.dto;

import com.example.touristguide.domain.user.Tip;

public class UserUpdateDto {
    private int user_id;
    private String firstname;
    private String lastname;
    private String email;
    private Tip tip;

    public UserUpdateDto(int user_id, String firstname, String lastname, String email, Tip tip) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.tip = tip;
    }

    public UserUpdateDto() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }
}
