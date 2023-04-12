package com.drawtok.tlsoup.model;

import java.util.List;

public class LoaiSpModel {
    boolean success;
    String message;
    List<LoaiSp> result;

    public boolean isSuccess() {
        return success;
    }


    public String getMessage() {
        return message;
    }


    public List<LoaiSp> getResult() {
        return result;
    }

}
