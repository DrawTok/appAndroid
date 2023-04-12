package com.drawtok.foodsoup.Model;

import java.io.Serializable;

public class Store implements Serializable {
    int storeID;
    String nameStore;
    int numReview;
    String imageStore;

    public Store(int storeID, String nameStore, int numReview, String imageStore) {
        this.storeID = storeID;
        this.nameStore = nameStore;
        this.numReview = numReview;
        this.imageStore = imageStore;
    }

    public int getStoreID() {
        return storeID;
    }

    public String getNameStore() {
        return nameStore;
    }

    public int getNumReview() {
        return numReview;
    }

    public String getImageStore() {
        return imageStore;
    }
}
