package com.drawtok.foodsoup.Model;

public class Photo {
    int bannerAdsID;
    String urlBanner;

    public Photo(int bannerAdsID, String urlBanner) {
        this.bannerAdsID = bannerAdsID;
        this.urlBanner = urlBanner;
    }

    public int getBannerAdsID() {
        return bannerAdsID;
    }

    public String getUrlBanner() {
        return urlBanner;
    }
}
