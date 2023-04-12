package com.drawtok.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imvDisc, imvSkipPrevious, imvFastRewind,
              imvPause, imvPlay, imvFastForward, imvSkipNext;

    TextView tvNameSong, tvTimeStart, tvTimeEnd;
    SeekBar skBSong;

    ArrayList<Music> arrayMusic;
    MediaPlayer mediaPlayer;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapping();
        AddMusic();

        imvDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        imvPlay.setOnClickListener(v->
        {
            if(mediaPlayer.isPlaying())
            {
                mediaPlayer.pause();
                imvPlay.setVisibility(View.VISIBLE);
                imvPause.setVisibility(View.INVISIBLE);
            }else
            {
                mediaPlayer.start();
                imvPlay.setVisibility(View.INVISIBLE);
                imvPause.setVisibility(View.VISIBLE);
            }
            getTimeSong();
            updateTimeSong();
        });

        imvPause.setOnClickListener(v->
        {
            imvDisc.clearAnimation();
            imvPlay.setVisibility(View.VISIBLE);
            imvPause.setVisibility(View.INVISIBLE);
            mediaPlayer.pause();
        });

        imvSkipPrevious.setOnClickListener(v ->
        {
            if(pos == 0)
            {
                pos = (arrayMusic.size()-1);
            }else
            {
                pos--;
            }
            setPause();
        });

        imvSkipNext.setOnClickListener(v ->
        {
            if(pos == (arrayMusic.size()-1))
            {
                pos = 0;
            }else
            {
                pos++;
            }
            setPause();
        });

        skBSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    private void HandleMediaPlayer(int i)
    {
        tvNameSong.setText(arrayMusic.get(i).getNameSong());
        mediaPlayer = MediaPlayer.create
                (this, arrayMusic.get(i).getPathToSong());
    }

    private void setPause()
    {
        mediaPlayer.stop();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_disc);
        imvDisc.startAnimation(animation);
        imvPause.setVisibility(View.VISIBLE);
        imvPlay.setVisibility(View.INVISIBLE);
        HandleMediaPlayer(pos);
        mediaPlayer.start();
        getTimeSong();
        updateTimeSong();
    }

    private void getTimeSong()
    {
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        tvTimeEnd.setText(format.format(mediaPlayer.getDuration()));
        /*skBSong.setProgress(0);*/
        skBSong.setMax(mediaPlayer.getDuration());
    }

    private void updateTimeSong()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                tvTimeStart.setText(format.format(mediaPlayer.getCurrentPosition()));
                skBSong.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);
                if(skBSong.getMax() == skBSong.getProgress())
                {
                    if(pos == (arrayMusic.size()-1))
                    {
                        pos = 0;
                    }else
                    {
                        pos++;
                    }
                    setPause();
                }
            }
        },100);
    }

    @Override
    protected void onStart() {
        super.onStart();
        HandleMediaPlayer(pos);
        getTimeSong();
    }

    /*@Override
    protected void onRestart() {
        super.onRestart();
        imvPlay.setVisibility(View.INVISIBLE);
        imvPause.setVisibility(View.VISIBLE);
        updateTimeSong();
        getTimeSong();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_disc);
        imvDisc.startAnimation(animation);
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
    }*/

    private void AddMusic() {
        arrayMusic = new ArrayList<>();
        arrayMusic.add(new Music("Bài này không để đi diễn", R.raw.bai_nay_ko_de_di_dien));
        arrayMusic.add(new Music("Chờ đợi có đáng sợ", R.raw.cho_doi_co_dang_do));
        arrayMusic.add(new Music("Chúng ta của hiện tại", R.raw.chung_ta_cua_hien_tai));
        arrayMusic.add(new Music("Một thời nhanh như một ngày", R.raw.mot_thoi_nhanh_nhu_mot_ngay));
        arrayMusic.add(new Music("Ước mơ của mẹ", R.raw.uoc_mo_cua_me));
    }
    private void Mapping() {

        imvDisc         = findViewById(R.id.imv_disc);
        imvSkipPrevious = findViewById(R.id.imv_previous_skip);
        imvFastRewind   = findViewById(R.id.imv_fast_rewind);
        imvPause        = findViewById(R.id.imv_pause);
        imvPlay         = findViewById(R.id.imv_play);
        imvFastForward  = findViewById(R.id.imv_fast_forward);
        imvSkipNext     = findViewById(R.id.imv_skip_next);
        tvNameSong      = findViewById(R.id.tv_name_song);
        tvTimeStart     = findViewById(R.id.tv_time_start);
        tvTimeEnd       = findViewById(R.id.tv_time_end);
        skBSong         = findViewById(R.id.sk_time);

        imvDisc.setClipToOutline(true);
    }
}