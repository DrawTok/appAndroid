package com.drawtok.imagepair;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvPoint, tvCountdownTime;
    ImageView imageQuestion, imageAnswer, btnMenu;
    public static ArrayList<String> arrayList;
    int REQUEST_CODE = 1211;
    String nameImage;
    public static int point = 100;
    SharedPreferences savePoint;
    private long time = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapping();

        savePoint = getSharedPreferences("currentPoint", MODE_PRIVATE);
        // thêm tên hình ảnh vào mảng
        String[] arrName = getResources().getStringArray(R.array.list_answer);
        arrayList = new ArrayList<>(Arrays.asList(arrName));
        // trộn mạng
        Collections.shuffle(arrayList);
        nameImage = arrayList.get(0);
        //gán câu hỏi hình ảnh
        int idImage = getResources().getIdentifier(
                arrayList.get(0), "drawable", getPackageName());
        imageQuestion.setImageResource(idImage);

        imageAnswer.setOnClickListener(v ->
        {
            startActivityForResult(
                    new Intent(MainActivity.this,
                            pickImageActivity.class), REQUEST_CODE);
        });

        btnMenu.setOnClickListener(v -> showMenu());
    }

    private void showMenu() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, btnMenu);
        popupMenu.getMenu().add("Điểm cao nhất");
        popupMenu.getMenu().add("Thoát");
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getTitle() == "Điểm cao nhất")
                {
                    startActivity(new Intent(MainActivity.this, maxScoreActivity.class));
                    return true;
                }
                if(item.getTitle() == "Thoát")
                {
                    finish();
                    System.exit(0);
                }
                return false;
            }
        });
    }

    private void Mapping() {
        tvPoint = findViewById(R.id.tv_point);
        tvCountdownTime = findViewById(R.id.tv_countdown_time);
        imageQuestion = findViewById(R.id.image_question);
        imageAnswer = findViewById(R.id.image_answer);
        btnMenu = findViewById(R.id.btn_menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(REQUEST_CODE == requestCode && resultCode == RESULT_OK && data != null)
        {
            String nameImageReceive = data.getStringExtra("namePicked");
            int idImageReceive = getResources().getIdentifier(
                    nameImageReceive, "drawable", getPackageName());
            imageAnswer.setImageResource(idImageReceive);
            if(nameImage.equals(nameImageReceive))
            {
                Toast.makeText(this, "Chính xác", Toast.LENGTH_SHORT).show();
                point += 10;
                countdown();
                loadNewQuestion();
                funcSavePoint();
            }else
            {
                Toast.makeText(this, "Bạn sai rồi!!", Toast.LENGTH_SHORT).show();
                countdown();
                loadNewQuestion();
                point -= 10;
                funcSavePoint();
            }
        }
        if(requestCode == REQUEST_CODE && resultCode == RESULT_CANCELED )
        {
            Toast.makeText(this, "Bạn muốn xem lại à:(( -20 điểm", Toast.LENGTH_SHORT).show();
            point -= 20;
            countdown();
            loadNewQuestion();
            funcSavePoint();
        }
        tvPoint.setText(point+"");
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void funcSavePoint() {
        SharedPreferences.Editor editor = savePoint.edit();
        editor.putInt("point", point);
        editor.commit();
    }

    private void loadNewQuestion() {
        imageAnswer.setEnabled(false);
        new CountDownTimer(2000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                // trộn mảng
                Collections.shuffle(arrayList);
                nameImage = arrayList.get(0);
                //gán câu hỏi hình ảnh
                int idImage = getResources().getIdentifier(
                        arrayList.get(0), "drawable", getPackageName());
                imageQuestion.setImageResource(idImage);
                imageAnswer.setImageResource(R.drawable.question);
                imageAnswer.setEnabled(true);
            }
        }.start();
    }

    @Override
    protected void onStart() {
        //gán điểm đã lưu của người chơi
        point = savePoint.getInt("point", 100);
        tvPoint.setText(point+"");
        //countdown();
        super.onStart();
    }

    private void countdown()
    {
        new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                updateTime();
            }

            @Override
            public void onFinish() {
                //hiệu ứng hình ảnh
                /*Animation animation = AnimationUtils.loadAnimation
                        (MainActivity.this, R.anim.animaton_img);
                imageQuestion.startAnimation(animation);*/
                time = 3000;
                checkPoint();
            }
        }.start();
    }

    private void updateTime()
    {
        int sec = (int) (time / 1000);
        String timeFormat = String.format(Locale.getDefault(), "%02d", sec);
        tvCountdownTime.setText(timeFormat);
    }

    private void checkPoint()
    {
        if(point <= 0)
        {
            point = 100;
            funcSavePoint();
        }
        tvPoint.setText(point+"");
    }
}
