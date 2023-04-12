package com.drawtok.foodsoup.Model;

import java.io.Serializable;

public class MenuStore implements Serializable {
    private final String nameFood;
    private final String title;
    private final int priceFood;
    private final String imageFood;

    public MenuStore(String nameFood, String title, int priceFood, String imageFood) {
        this.nameFood = nameFood;
        this.title = title;
        this.priceFood = priceFood;
        this.imageFood = imageFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public String getTitle() {
        return title;
    }

    public int getPriceFood() {
        return priceFood;
    }

    public String getImageFood() {
        return imageFood;
    }
}
