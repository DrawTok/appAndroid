package com.drawtok.animalcrossing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView money;
    Button btn;
    SeekBar skF, skS, skT;
    CheckBox cbFirst, cbSecond, cbThird;
    int totalMoney = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewToImage();
        DisableSeekBar();
        check_CheckBox();
        money.setText(totalMoney+"");

        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int num = randomSpeedPet();
                skF.setProgress(skF.getProgress()+num);
                num = randomSpeedPet();
                skS.setProgress(skS.getProgress()+num);
                num = randomSpeedPet();
                skT.setProgress(skT.getProgress()+num);

                if(skF.getProgress() >= skF.getMax())
                {
                    this.cancel();
                    if(cbFirst.isChecked())
                    {
                        totalMoney += 10;
                        Toast.makeText(MainActivity.this, "Linh thú của bạn về nhất." +
                                "Bạn đã thắng !!!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "+10$",
                                Toast.LENGTH_SHORT).show();
                    }else
                    {
                        totalMoney -= 10;
                        Toast.makeText(MainActivity.this, "Linh thú thứ nhất về đích." +
                                "Bạn thua rồi :((", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "-10$",
                                Toast.LENGTH_SHORT).show();
                    }
                    EnableCheckBox();
                }

                if(skS.getProgress() >= skS.getMax())
                {
                    this.cancel();
                    if(cbSecond.isChecked())
                    {
                        totalMoney += 10;
                        Toast.makeText(MainActivity.this, "Linh thú của bạn về nhất." +
                                "Bạn đã thắng !!!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "+10$",
                                Toast.LENGTH_SHORT).show();
                    }else
                    {
                        totalMoney -= 10;
                        Toast.makeText(MainActivity.this, "Linh thú thứ 2 về đích." +
                                "Bạn thua rồi :((", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "-10$",
                                Toast.LENGTH_SHORT).show();

                    }
                    EnableCheckBox();
                }

                if(skT.getProgress() >= skT.getMax())
                {
                    this.cancel();
                    if(cbThird.isChecked())
                    {
                        totalMoney += 10;
                        Toast.makeText(MainActivity.this, "Linh thú của bạn về nhất." +
                                "Bạn đã thắng !!!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "+10$",
                                Toast.LENGTH_SHORT).show();
                    }else
                    {
                        totalMoney -= 10;
                        Toast.makeText(MainActivity.this, "Linh thú thứ ba về đích." +
                                "Bạn thua rồi :((", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "-10$",
                                Toast.LENGTH_SHORT).show();
                    }
                    EnableCheckBox();
                }
                money.setText(totalMoney+"");
                checkMoney();
            }

            @Override
            public void onFinish() {

            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbFirst.isChecked() || cbSecond.isChecked() || cbThird.isChecked())
                {
                    skF.setProgress(0);
                    skS.setProgress(0);
                    skT.setProgress(0);
                    countDownTimer.start();
                    btn.setEnabled(false);
                    DisableCheckBox();

                }else
                {
                    Toast.makeText(MainActivity.this,
                            "Bạn chưa chọn linh thú để thi đấu...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int randomSpeedPet() {
        Random random = new Random();
        int speed = random.nextInt(3);
        return speed;
    }

    private void check_CheckBox() {
        cbFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    cbSecond.setChecked(false);
                    cbThird.setChecked(false);
                }
            }
        });
        cbSecond.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    cbFirst.setChecked(false);
                    cbThird.setChecked(false);
                }
            }
        });
        cbThird.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    cbFirst.setChecked(false);
                    cbSecond.setChecked(false);
                }
            }
        });
    }

    private void ViewToImage() {
        money = findViewById(R.id.txtMoney);
        btn = findViewById(R.id.btnAccept);
        skF = findViewById(R.id.skFDistance);
        skS = findViewById(R.id.skSSDistance);
        skT = findViewById(R.id.skTDistance);
        cbFirst = findViewById(R.id.first_check);
        cbSecond = findViewById(R.id.second_check);
        cbThird = findViewById(R.id.third_check);
    }

    private void EnableCheckBox()
    {
        cbFirst.setEnabled(true);
        cbSecond.setEnabled(true);
        cbThird.setEnabled(true);
        btn.setEnabled(true);
    }

    private void DisableCheckBox()
    {
        cbFirst.setEnabled(false);
        cbSecond.setEnabled(false);
        cbThird.setEnabled(false);
    }

    private void DisableSeekBar()
    {
        skF.setEnabled(false);
        skS.setEnabled(false);
        skT.setEnabled(false);
    }

    private void checkMoney()
    {
        if(totalMoney <= 0)
        {
            Toast.makeText(MainActivity.this, "Bạn đã hết tiền",
                    Toast.LENGTH_SHORT).show();
            btn.setEnabled(false);
        }
    }
}