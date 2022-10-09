package com.drawtok.footballvn;

public class PlayerInfo {
    String name, dob;
    int imgPlayer, imgFlag;

    public PlayerInfo() {}

    public PlayerInfo(String name, String dob, int imgPlayer, int imgFlag) {
        this.name = name;
        this.dob = dob;
        this.imgPlayer = imgPlayer;
        this.imgFlag = imgFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getImgPlayer() {
        return imgPlayer;
    }

    public void setImgPlayer(int imgPlayer) {
        this.imgPlayer = imgPlayer;
    }

    public int getImgFlag() {
        return imgFlag;
    }

    public void setImgFlag(int imgFlag) {
        this.imgFlag = imgFlag;
    }
}
