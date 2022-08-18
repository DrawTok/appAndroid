package com.drawtok.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    RadioGroup radGroup;
    RadioButton mRad, lRad, tRad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewToImage();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = "";
                if(mRad.isChecked())
                {
                    time += mRad.getText();
                }

                if(lRad.isChecked())
                {
                    time += lRad.getText();
                }

                if(tRad.isChecked())
                {
                    time += tRad.getText();
                }

                Toast.makeText(MainActivity.this, time, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ViewToImage() {
        btn = findViewById(R.id.btnAccept);
        radGroup = findViewById(R.id.radioGroup);
        mRad = findViewById(R.id.radioMorning);
        lRad = findViewById(R.id.radioLunch);
        tRad = findViewById(R.id.radioTonight);
    }
}