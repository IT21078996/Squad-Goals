package com.example.madproject;

public class User {
    public String email;
    public String dob;
    public String mobile;
    public String address;

    public User() {
    }

    public User(String email, String dob, String mobile, String address) {
        this.email = email;
        this.dob = dob;
        this.mobile = mobile;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
