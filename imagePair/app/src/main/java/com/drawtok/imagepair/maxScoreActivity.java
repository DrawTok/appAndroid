package com.drawtok.imagepair;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class maxScoreActivity extends AppCompatActivity {

    TextView tvScoreMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_score);

        tvScoreMax = findViewById(R.id.tv_score_max);

        tvScoreMax.setText(MainActivity.point+"");
    }
}