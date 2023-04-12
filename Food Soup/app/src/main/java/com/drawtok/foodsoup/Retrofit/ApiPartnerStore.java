package com.drawtok.foodsoup.Retrofit;

import com.drawtok.foodsoup.Model.StoreModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiPartnerStore {
    @GET("getStore.php")
    Observable<StoreModel> getStore();
}
