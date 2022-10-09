package com.drawtok.intentexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import kotlin.ranges.IntRange;

public class MainActivity extends AppCompatActivity {

    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btn_SwitchScreen);

        btnSend.setOnClickListener(v ->
        {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            String[] ArrayCourse = {"IOS", "Android", "PHP", "JS"};
            intent.putExtra("data", ArrayCourse);
            startActivity(intent);
        });

    }
}