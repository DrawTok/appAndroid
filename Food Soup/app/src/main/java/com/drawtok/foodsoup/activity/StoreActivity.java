package com.drawtok.foodsoup.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.drawtok.foodsoup.Adapter.MenuStoreAdapter;
import com.drawtok.foodsoup.Interface.RecycleViewInterface;
import com.drawtok.foodsoup.Model.MenuStore;
import com.drawtok.foodsoup.Model.Store;
import com.drawtok.foodsoup.R;
import com.drawtok.foodsoup.Retrofit.ApiMenuStore;
import com.drawtok.foodsoup.Retrofit.RetrofitClient;
import com.drawtok.foodsoup.Utils.Utils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class StoreActivity extends AppCompatActivity implements RecycleViewInterface {

    private ImageView imvStore;
    private TextView tvNameStore;
    private RecyclerView recFood, recDrink;
    List<MenuStore> menuStoreFood;
    List<MenuStore> menuStoreDrink;
    ApiMenuStore apiMenuStore;
    MenuStoreAdapter menuStoreAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Mapping();
        createApi();
        getStoreInfo();
    }

    private void createApi() {
        apiMenuStore = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiMenuStore.class);
    }

    private void Mapping() {
        imvStore            = findViewById(R.id.imv_bg_store);
        tvNameStore         = findViewById(R.id.tv_info_name_store);
        recFood   = findViewById(R.id.list_menu_of_store);
        recDrink = findViewById(R.id.list_menu_drink);


    }

    private void getStoreInfo() {
        Intent intent = getIntent();
        Store store = (Store) intent.getSerializableExtra("storeInfo");
        Glide.with(this).load(store.getImageStore()).into(imvStore);
        tvNameStore.setText(store.getNameStore());

        compositeDisposable.add(apiMenuStore.sendData(store.getStoreID(), 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        menuStoreModel->
                        {
                            if(menuStoreModel != null && menuStoreModel.isSuccess())
                            {
                                menuStoreFood = menuStoreModel.getResult();
                                menuStoreAdapter = new MenuStoreAdapter(
                                        getApplicationContext(), menuStoreFood,
                                        this, 1);

                                recFood.setAdapter(menuStoreAdapter);
                                recFood.setHasFixedSize(true);
                                recFood.setLayoutManager(new LinearLayoutManager(this));
                            }
                        }
                ));

        compositeDisposable.add(apiMenuStore.sendData(store.getStoreID(), 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        menuStoreModel->
                        {
                            if(menuStoreModel != null && menuStoreModel.isSuccess())
                            {
                                menuStoreDrink = menuStoreModel.getResult();
                                menuStoreAdapter = new MenuStoreAdapter(
                                        getApplicationContext(), menuStoreDrink,
                                        this, 2);

                                recDrink.setAdapter(menuStoreAdapter);
                                recDrink.setHasFixedSize(true);
                                recDrink.setLayoutManager(new LinearLayoutManager(this));
                            }
                        }
                ));
    }

    @Override
    public void onClickItem(int position, int recyclerViewId) {
        Intent intent = new Intent(StoreActivity.this, OrderActivity.class);
        if(recyclerViewId == 1)
        {
            intent.putExtra("itemInfo", menuStoreFood.get(position));
        }

        if(recyclerViewId == 2)
        {
            intent.putExtra("itemInfo", menuStoreDrink.get(position));
        }
        startActivity(intent);
    }
}