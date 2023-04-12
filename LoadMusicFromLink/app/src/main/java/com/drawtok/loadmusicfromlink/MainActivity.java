package com.drawtok.loadmusicfromlink;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button btnLoadMusic, btnLoadVideo;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoadMusic = findViewById(R.id.btn_load_music);
        progressBar = findViewById(R.id.progress_bar);
        btnLoadVideo = findViewById(R.id.btn_load_video);
        videoView = findViewById(R.id.vv_mp4);

        progressBar.setVisibility(View.INVISIBLE);
        String url = "https://www.mboxdrive.com/chodoicodangso.mp3";
        btnLoadMusic.setOnClickListener(v ->
        {
            MediaPlayer media = new MediaPlayer();
            media.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                media.setDataSource(url);
                media.prepareAsync();
                progressBar.setVisibility(View.VISIBLE);
                media.setOnPreparedListener(mp -> {
                    progressBar.setVisibility(View.INVISIBLE);
                    mp.start();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnLoadVideo.setOnClickListener(v->
        {
            Uri uri = Uri.parse("https://khoapham.vn/download/vuoncaovietnam.mp4");
            videoView.setVideoURI(uri);
            videoView.setMediaController(new MediaController(MainActivity.this));
            videoView.start();

        });
    }
}