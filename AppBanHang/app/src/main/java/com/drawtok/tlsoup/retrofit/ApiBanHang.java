package com.drawtok.tlsoup.retrofit;

import com.drawtok.tlsoup.model.LoaiSpModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiBanHang {
    @GET("getSanpham.php")
    Observable<LoaiSpModel> getLoaiSp();
}
