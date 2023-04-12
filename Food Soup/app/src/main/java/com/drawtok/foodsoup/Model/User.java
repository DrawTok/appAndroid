package com.drawtok.foodsoup.Model;

public class User {
    String Username;
    String PhoneNumber;
    String GenderUser;

    public User(String Username, String PhoneNumber, String GenderUser) {
        this.Username = Username;
        this.PhoneNumber = PhoneNumber;
        this.GenderUser = GenderUser;

    }

    public String getUsername() {
        return Username;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getGenderUser() {
        return GenderUser;
    }
}
