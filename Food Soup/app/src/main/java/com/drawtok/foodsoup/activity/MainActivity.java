package com.drawtok.foodsoup.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.drawtok.foodsoup.Adapter.PhotoAdapter;
import com.drawtok.foodsoup.Adapter.StoreAdapter;
import com.drawtok.foodsoup.Interface.RecycleViewInterface;
import com.drawtok.foodsoup.Model.Photo;
import com.drawtok.foodsoup.Model.Store;
import com.drawtok.foodsoup.R;
import com.drawtok.foodsoup.Retrofit.ApiBannerAds;
import com.drawtok.foodsoup.Retrofit.ApiPartnerStore;
import com.drawtok.foodsoup.Retrofit.RetrofitClient;
import com.drawtok.foodsoup.Utils.Utils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements RecycleViewInterface {

    TextView tvSearchBar;
    ViewPager2 vppBannerAds;
    RecyclerView recyclerListFood;
    PhotoAdapter photoAdapter;
    StoreAdapter storeAdapter;
    ImageView imvMenu;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    ApiBannerAds apiBannerAds;
    ApiPartnerStore apiPartnerStore;

    List<Photo> listPhoto;
    List<Store> listStore;

    Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = vppBannerAds.getCurrentItem();
            if(currentPosition == listPhoto.size()-1)
            {
                vppBannerAds.setCurrentItem(0);
            }else
            {
                vppBannerAds.setCurrentItem(currentPosition+1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();
        createApi();


        if(isConnected(this))
        {
            runBannerAds();
            showListPartnerStores();
        }else
        {
            Toast.makeText(this, "Không có INTERNET." +
                    " Vui lòng kết nối lại", Toast.LENGTH_SHORT).show();
        }

        imvMenu.setOnClickListener(
                v->startActivity(new Intent(this, InfoAccountActivity.class)));

    }



    private void showListPartnerStores() {
        compositeDisposable.add(apiPartnerStore.getStore()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        storeModel ->
                        {
                            if(storeModel.isSuccess())
                            {
                                listStore = storeModel.getResult();
                                storeAdapter = new StoreAdapter(getApplicationContext(), listStore, this);
                                recyclerListFood.setAdapter(storeAdapter);
                                recyclerListFood.setLayoutManager(new LinearLayoutManager(this));
                            }
                        }
                ));
    }

    private void runBannerAds() {
        //setting viewpager2
        vppBannerAds.setOffscreenPageLimit(3);
        vppBannerAds.setClipToPadding(false);
        vppBannerAds.setClipChildren(false);
        vppBannerAds.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });
        vppBannerAds.setPageTransformer(compositePageTransformer);

        compositeDisposable.add(apiBannerAds.getPhoto()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    photoModel ->
                    {
                        if(photoModel.isSuccess())
                        {

                            listPhoto = photoModel.getResult();
                            photoAdapter = new PhotoAdapter(getApplicationContext(), listPhoto, vppBannerAds);
                            vppBannerAds.setAdapter(photoAdapter);
                        }
                    }
                ));


        vppBannerAds.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
    }

    private boolean isConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    private void createApi() {
        apiBannerAds = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBannerAds.class);
        apiPartnerStore = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiPartnerStore.class);
    }

    private void Mapping() {
        tvSearchBar         = findViewById(R.id.tv_search);
        vppBannerAds        = findViewById(R.id.view_banner_ads);
        recyclerListFood    = findViewById(R.id.recycle_list_food);
        imvMenu             = findViewById(R.id.imv_menu);
    }

    @Override
    public void onClickItem(int position, int recyclerViewId) {

        Intent intent = new Intent(MainActivity.this, StoreActivity.class);
        intent.putExtra("storeInfo", listStore.get(position));
        startActivity(intent);
    }

}