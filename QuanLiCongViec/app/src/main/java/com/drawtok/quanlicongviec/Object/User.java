package com.drawtok.quanlicongviec.Object;

public class User {
    private int UserID;
    private String Username;
    private String Password;
    private byte[] UserAvatar;

    public User(int userID, String username, String password, byte[] userAvatar) {
        UserID = userID;
        Username = username;
        Password = password;
        UserAvatar = userAvatar;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public byte[] getUserAvatar() {
        return UserAvatar;
    }

    public void setUserAvatar(byte[] userAvatar) {
        UserAvatar = userAvatar;
    }
}
