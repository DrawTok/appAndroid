package com.drawtok.mymusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button btnPlayMp3, btnPlayMp4;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlayMp3 = findViewById(R.id.play_mp3);
        btnPlayMp4 = findViewById(R.id.play_mp4);
        videoView = findViewById(R.id.vvw_mp4);

        btnPlayMp3.setOnClickListener(v ->
        {
            MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.vd_uoc_mo_cua_me);
            mediaPlayer.start();
        });

        btnPlayMp4.setOnClickListener(v ->
        {
            videoView.setVideoURI(
                    Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.vd_uoc_mo_cua_me));

            videoView.start();
            MediaController mediaController = new MediaController(this);
            mediaController.setMediaPlayer(videoView);
            videoView.setMediaController(mediaController);
        });
    }
}