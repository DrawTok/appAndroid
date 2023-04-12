package com.drawtok.foodsoup.Retrofit;

import com.drawtok.foodsoup.Model.MenuStoreModel;
import com.drawtok.foodsoup.Model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiUser {
    @FormUrlEncoded
    @POST("index.php")
    Observable<UserModel> sendData(
            @Field("Username") String Username,
            @Field("PhoneNumber") String PhoneNumber,
            @Field("Password") String Password
    );
}
