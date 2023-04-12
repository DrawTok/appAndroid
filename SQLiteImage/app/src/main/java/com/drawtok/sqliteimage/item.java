package com.drawtok.sqliteimage;
public class item {
    private int id;
    private  String des;
    private byte[] image;

    public item(int id, String des, byte[] image) {
        this.id = id;
        this.des = des;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getDes() {
        return des;
    }

    public byte[] getImage() {
        return image;
    }

}
