package com.drawtok.intentexplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class SecondActivity extends AppCompatActivity {

    TextView txtRReceive;
    Button btnReceive;
    final int min = 0;
    final int max = 3;
    final int random = new Random().nextInt((max - min)+1)+min;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtRReceive = findViewById(R.id.tv_receive_data);
        btnReceive = findViewById(R.id.btn_MainAct);
        Intent intent = getIntent();
        String[] course = intent.getStringArrayExtra("data");
        txtRReceive.setText(course[random]);

        btnReceive.setOnClickListener(v ->
        {
            backToMainScreen();
        });

    }

    private void backToMainScreen() {

        Intent intent1 = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent1);

    }
}