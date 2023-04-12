package com.drawtok.tlsoup.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.drawtok.tlsoup.R;
import com.drawtok.tlsoup.adapter.LoaiSpAdapter;
import com.drawtok.tlsoup.model.LoaiSp;
import com.drawtok.tlsoup.retrofit.ApiBanHang;
import com.drawtok.tlsoup.retrofit.RetrofitClient;
import com.drawtok.tlsoup.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayout;
    LoaiSpAdapter loaiSpAdapter;
    List<LoaiSp> mangLoaiSp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapping();

        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);

        ActionBar();

        if(isConnected(this))
        {
            ActionViewFilpper();
            getLoaiSanPham();
        }else
        {
            Toast.makeText(this, "Không có INTERNET." +
                    " Vui lòng kết nối lại", Toast.LENGTH_SHORT).show();
        }
    }

    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanHang.getLoaiSp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if( loaiSpModel.isSuccess())
                            {
                                /*mangLoaiSp = loaiSpModel.getResult();
                                loaiSpAdapter = new LoaiSpAdapter(getApplicationContext(), mangLoaiSp);
                                listViewManHinhChinh.setAdapter(loaiSpAdapter);*/
                                Toast.makeText(this, loaiSpModel.getResult().get(0).getHinhanh()+"", Toast.LENGTH_SHORT).show();
                            }
                        }
                ));
    }

    private void ActionViewFilpper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cf.shopee.vn/file/fa79715264f5c973648d8096a8aa9773_xxhdpi");
        mangquangcao.add("https://cf.shopee.vn/file/vn-50009109-05021fee153582de63a58b8e9faa9ea0_xxhdpi");
        mangquangcao.add("https://cf.shopee.vn/file/vn-50009109-5d00fcc54a03e33183be7451e8e310cd_xxhdpi");
        for(int i = 0; i < mangquangcao.size(); i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);

        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slideOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slideIn);
        viewFlipper.setOutAnimation(slideOut);

    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
    }

    private void Mapping() {
        toolbar                     = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper                 = findViewById(R.id.viewFlipper);
        recyclerViewManHinhChinh    = findViewById(R.id.recycleview);
        navigationView              = findViewById(R.id.navigationview);
        listViewManHinhChinh        = findViewById(R.id.listviewmanhinhchinh);
        drawerLayout                = findViewById(R.id.drawerLayout);

        //Khởi tạo list
        mangLoaiSp = new ArrayList<>();

    }

    private boolean isConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting())
                return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}