package com.drawtok.tlsoup.model;

public class LoaiSp {
    int sanphamID;
    String tensanpham;
    String hinhanh;

    public LoaiSp(int sanphamID, String tensanpham, String hinhanh) {
        this.sanphamID = sanphamID;
        this.tensanpham = tensanpham;
        this.hinhanh = hinhanh;
    }

    public int getSanphamID() {
        return sanphamID;
    }


    public String getTensanpham() {
        return tensanpham;
    }


    public String getHinhanh() {
        return hinhanh;
    }

}
