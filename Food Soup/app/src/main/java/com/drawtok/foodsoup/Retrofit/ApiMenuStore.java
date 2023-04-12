package com.drawtok.foodsoup.Retrofit;


import com.drawtok.foodsoup.Model.MenuStoreModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiMenuStore {

    @FormUrlEncoded
    @POST("getMenuStore.php")
    Observable<MenuStoreModel> sendData(
            @Field("idStore") int idStore,
            @Field("foodDrink") int foodDrink
    );
}
