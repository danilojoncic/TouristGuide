package com.example.touristguide.dto;

import com.example.touristguide.domain.user.Tip;

public class CreateUserDto {
    private String firstname;
    private String lastname;
    private Tip tip;
    private String email;
    private String password;

    public CreateUserDto(String firstname, String lastname, Tip tip, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.tip = tip;
        this.email = email;
        this.password = password;
    }

    public CreateUserDto() {
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

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
