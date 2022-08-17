package com.drawtok.random;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText firstValue, secondValue;
    Button btnRandom;
    TextView textDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ
        ViewToImage();

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fValue = firstValue.getText().toString().trim();
                String sValue = secondValue.getText().toString().trim();

                if(fValue.length() == 0 || sValue.length() == 0)
                {
                    Toast.makeText(MainActivity.this,
                            "Nhập đầy đủ giá trị", Toast.LENGTH_LONG).show();
                }else{
                    int min = Integer.parseInt(fValue);
                    int max = Integer.parseInt(sValue);
                    if(min > max)
                    {
                        Toast.makeText(MainActivity.this,
                                "Giá trị thứ nhất phải nhỏ hơn giá trị thứ 2",
                                Toast.LENGTH_LONG).show();
                    }else
                    {
                        Random random = new Random();
                        int numRandom = random.nextInt(max - min + 1) + min;
                        textDisplay.setText(numRandom + "");
                    }

                }
            }
        });
    }
    private void ViewToImage()
    {
        firstValue = findViewById(R.id.first_value);
        secondValue = findViewById(R.id.second_value);
        btnRandom = findViewById(R.id.btnRandom);
        textDisplay = findViewById(R.id.textDisplay);
    }
}