package com.example.ezchats.model;

import com.google.firebase.Timestamp;

public class User {
    public String phone;

    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Timestamp getCurrentTimestamp() {
        return currentTimestamp;
    }

    public void setCurrentTimestamp(Timestamp currentTimestamp) {
        this.currentTimestamp = currentTimestamp;
    }

    public User(String phone, String firstName, Timestamp currentTimestamp,String userId) {
        this.phone = phone;
        this.firstName = firstName;
        this.currentTimestamp = currentTimestamp;
        this.userId=userId;
    }

    public String firstName;
    public Timestamp currentTimestamp;
    public  String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
