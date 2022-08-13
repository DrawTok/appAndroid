package com.drawtok.baseballcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView scoreA;
    private TextView scoreB;
    private Button btn1A, btn2A, btn3A,
            btn1B, btn2B, btn3B, btnReset;

    private int currentCounterA = 0;
    private int currentCounterB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapIdToView();
        setupView();
        update();
    }

    private void mapIdToView()
    {
        scoreA = findViewById(R.id.scoreA);
        scoreB = findViewById(R.id.scoreB);
        btn1A = findViewById(R.id.btn1A);
        btn2A = findViewById(R.id.btn2A);
        btn3A = findViewById(R.id.btn3A);
        btn1B = findViewById(R.id.btn1B);
        btn2B = findViewById(R.id.btn2B);
        btn3B = findViewById(R.id.btn3B);
        btnReset = findViewById(R.id.btnReset);
    }

    private void update()
    {
        scoreA.setText(String.valueOf(currentCounterA));
        scoreB.setText(String.valueOf(currentCounterB));
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn1A:
                currentCounterA+=1;
                break;
            case R.id.btn2A:
                currentCounterA+=2;
                break;
            case R.id.btn3A:
                currentCounterA+=3;
                break;
            case R.id.btn1B:
                currentCounterB+=1;
                break;
            case R.id.btn2B:
                currentCounterB+=2;
                break;
            case R.id.btn3B:
                currentCounterB+=3;
                break;
            case R.id.btnReset:
                currentCounterA = currentCounterB = 0;
                break;
        }
        update();
    }

    private void setupView()
    {
        btn1A.setOnClickListener(this);
        btn1B.setOnClickListener(this);
        btn2A.setOnClickListener(this);
        btn2B.setOnClickListener(this);
        btn3A.setOnClickListener(this);
        btn3B.setOnClickListener(this);
        btnReset.setOnClickListener(this);

    }
}