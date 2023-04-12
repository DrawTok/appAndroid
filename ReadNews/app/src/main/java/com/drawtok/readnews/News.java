package com.drawtok.readnews;

import android.graphics.Bitmap;

public class News {
    String title, time, link, linkImage;

    public News(){}

    public News(String linkImage, String title, String time, String link) {
        this.linkImage = linkImage;
        this.title = title;
        this.time= time;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }


    public String getTime() {
        return time;
    }

    public String getLinkImage() {
        return linkImage;
    }
}
