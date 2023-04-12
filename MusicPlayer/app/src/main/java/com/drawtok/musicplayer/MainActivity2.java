package com.drawtok.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    ProgressBar progressBar;
    Button btnLoadMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnLoadMusic = findViewById(R.id.btn_load_music);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        String url = "https://khoapham.vn/download/vietnamoi.mp3";
        btnLoadMusic.setOnClickListener(v ->
        {
            MediaPlayer media = new MediaPlayer();
            media.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                media.setDataSource(url);
                media.prepareAsync();
                progressBar.setVisibility(View.VISIBLE);
                media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        progressBar.setVisibility(View.INVISIBLE);
                        mp.start();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}