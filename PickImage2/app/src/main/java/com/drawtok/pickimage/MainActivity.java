package com.drawtok.pickimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView tvPoint;
    ImageView imgVQuestion, imgVAnswer;
    public static ArrayList<String> arrayList;
    int REQUEST_CODE_IMG = 1211;
    String nameImgQuestion;
    int point = 100;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPoint         = findViewById(R.id.tv_point);
        imgVQuestion    = findViewById(R.id.imgV_question);
        imgVAnswer      = findViewById(R.id.imgV_answer);

        sharedPreferences = getSharedPreferences("currentPoint", MODE_PRIVATE);
        point = sharedPreferences.getInt("point", 100);
        tvPoint.setText(point+"");

        String[] arrName = getResources().getStringArray(R.array.list_img);
        arrayList = new ArrayList<>(Arrays.asList(arrName));

        //trộn mảng
        Collections.shuffle(arrayList);
        nameImgQuestion = arrayList.get(0);
        int idImg = getResources().getIdentifier(arrayList.get(0), "drawable", getPackageName());
        imgVQuestion.setImageResource(idImg);

        imgVAnswer.setOnClickListener(v ->
        {
            startActivityForResult(new Intent(
                    MainActivity.this, ImgActivity.class), REQUEST_CODE_IMG);
        });

        sharedPreferences = getSharedPreferences("currentPoint", MODE_PRIVATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_IMG && resultCode == RESULT_OK && data != null)
        {
            String namePickReceive = data.getStringExtra("nameImgPick");
            int idReceive = getResources().getIdentifier(
                    namePickReceive, "drawable", getPackageName());
            imgVAnswer.setImageResource(idReceive);
            if(namePickReceive.equals(nameImgQuestion))
            {
                point+=10;
                savePoint();
                Toast.makeText(this, "Chính xác!!!", Toast.LENGTH_SHORT).show();
                new CountDownTimer(2000, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        //trộn mảng
                        Collections.shuffle(arrayList);
                        nameImgQuestion = arrayList.get(0);
                        int idImg = getResources().getIdentifier(arrayList.get(0), "drawable", getPackageName());
                        imgVQuestion.setImageResource(idImg);
                    }
                }.start();
            }else
            {
               if(point >= 10)
               {
                   point-=10;
               }
                savePoint();
                Toast.makeText(this, "Bạn đã sai... ", Toast.LENGTH_SHORT).show();
            }
            tvPoint.setText(point+"");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void savePoint() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("point", point);
        editor.commit();
    }
}

