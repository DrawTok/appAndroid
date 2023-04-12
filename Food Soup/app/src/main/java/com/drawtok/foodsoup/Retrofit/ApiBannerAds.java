package com.drawtok.foodsoup.Retrofit;

import com.drawtok.foodsoup.Model.PhotoModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiBannerAds {
    @GET("getAdsBanner.php")
    Observable<PhotoModel> getPhoto();
}
