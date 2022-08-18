package com.drawtok.assign_wallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnSwitch;
    ConstraintLayout setWallpaper;
    ArrayList<Integer> arrayImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWallpaper = findViewById(R.id.wallpaper);
        btnSwitch = findViewById(R.id.btnSwitch);

        arrayImages = new ArrayList<>();
        arrayImages.add(R.drawable.wallpaper_1);
        arrayImages.add(R.drawable.wallpaper_2);
        arrayImages.add(R.drawable.wallpaper_3);
        arrayImages.add(R.drawable.wallpaper_4);

        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int pos = random.nextInt(arrayImages.size());

                setWallpaper.setBackgroundResource(arrayImages.get(pos));
            }
        });

    }
}