package com.drawtok.foodsoup.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.drawtok.foodsoup.Model.MenuStore;
import com.drawtok.foodsoup.R;
import com.drawtok.foodsoup.Utils.Utils;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    TextView tvNameItem, tvTitleItem, tvPriceItem, tvNumItem;
    EditText tvNoteItem;
    ImageView imvItem;
    ImageButton imgBtnDecrease, imgBtnIncrease;
    MaterialButton btnAddCart;
    int numItem = 1;
    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Mapping();

        showData();
    }

    private void showData() {
        Intent intent = getIntent();
        MenuStore menuStore = (MenuStore) intent.getSerializableExtra("itemInfo");
        tvNameItem.setText(menuStore.getNameFood());
        tvTitleItem.setText(menuStore.getTitle());
        String price = menuStore.getPriceFood()+"";
        tvPriceItem.setText(String.format("%s%s", price, getString(R.string.currency_unit)));
        btnAddCart.setText(String.format("%s%s", getString(R.string.add_cart), "  * "+price));
        Glide.with(this).load(menuStore.getImageFood()).into(imvItem);

        tvNumItem.setText(String.format("%s%s", "0", numItem));
        imgBtnDecrease.setEnabled(false);

        imgBtnIncrease.setOnClickListener(v->
        {
            String num;
            numItem += 1;
            if(numItem < 10)
                num = "0"+numItem;
            else
                num = numItem+"";
            tvNumItem.setText(num);
            int pay = menuStore.getPriceFood()*numItem;
            btnAddCart.setText(String.format("%s%s", getString(R.string.add_cart), "  * "+pay));
            if(numItem == 2)
            {
                imgBtnDecrease.setEnabled(true);
            }
            if(numItem == 15)
                imgBtnIncrease.setEnabled(false);
        });

        imgBtnDecrease.setOnClickListener(v->
        {
            String num;
            numItem -= 1;
            int pay = menuStore.getPriceFood()*numItem;
            btnAddCart.setText(String.format("%s%s", getString(R.string.add_cart), "  * "+pay));
            if(numItem <= 1) {
                imgBtnDecrease.setEnabled(false);
                numItem = 1;
            }

            if(numItem == 14)
                imgBtnIncrease.setEnabled(true);
            if(numItem < 10)
            {
                num = "0"+numItem;
            }else
            {
                num = numItem+"";
            }
            tvNumItem.setText(num);

        });

        tvNoteItem.setOnClickListener(v->
        {
            Intent noteIntent = new Intent(OrderActivity.this, NoteForStoreActivity.class);
            noteIntent.putExtra("noteForStore", tvNoteItem.getText().toString());
            startActivityForResult(noteIntent, 1);
        });

        btnAddCart.setOnClickListener(v->
        {
            //Add item to card
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            text = data.getStringExtra("note");
            if(text != null)
                tvNoteItem.setText(text);
        }
    }

    private void Mapping() {
        imvItem         = findViewById(R.id.imv_item_food);
        tvNameItem      = findViewById(R.id.tv_name_item);
        tvTitleItem     = findViewById(R.id.tv_title_item);
        tvPriceItem     = findViewById(R.id.tv_price_item);
        tvNoteItem       = findViewById(R.id.tv_note_item);
        tvNumItem       = findViewById(R.id.number_item);
        imgBtnDecrease  = findViewById(R.id.decrease_button);
        imgBtnIncrease  = findViewById(R.id.increase_button);
        btnAddCart      = findViewById(R.id.btn_add_cart);
    }

}