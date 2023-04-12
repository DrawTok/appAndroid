package com.drawtok.foodsoup.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.drawtok.foodsoup.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class InfoAccountActivity extends AppCompatActivity {

    ImageView imvBack, edtProfile;
    RoundedImageView imvAvatar;
    TextView tvNameUser, tvCart, tvManageCard,
            tvNotification, tvSupport, tvLogout;


    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_account);
        Mapping();

        imvBack.setOnClickListener(v->finish());
        getInfo();

        tvLogout.setOnClickListener(v->
        {
            sharedPreferences.edit().putBoolean("isFirstRun", true).apply();
            startActivity(new Intent(this, LoginActivity.class));
        });

    }

    private void getInfo() {
        sharedPreferences = getSharedPreferences("infoUser", MODE_PRIVATE);
        String name = sharedPreferences.getString("Username", "");
        tvNameUser.setText(name);
    }

    private void Mapping() {
        imvBack         = findViewById(R.id.imv_back);
        edtProfile      = findViewById(R.id.imv_edit_profile);
        imvAvatar       = findViewById(R.id.imv_user);
        tvNameUser      = findViewById(R.id.tv_name_user);
        tvCart          = findViewById(R.id.tv_cart);
        tvManageCard    = findViewById(R.id.tv_manage_card);
        tvNotification  = findViewById(R.id.tv_notification);
        tvSupport       = findViewById(R.id.tv_support);
        tvLogout        = findViewById(R.id.tv_logout);
    }
}