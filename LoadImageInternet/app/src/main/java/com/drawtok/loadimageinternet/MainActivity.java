package com.drawtok.loadimageinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imgDisplay;
    Button btnLoadImage, btnActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgDisplay = findViewById(R.id.imgV_display);
        btnLoadImage = findViewById(R.id.btn_loadImg);
        btnActivity2 = findViewById(R.id.btn_Switch);

        String link = "https://img4.thuthuatphanmem.vn/uploads/2020/05/13/anh-nen-4k-anime_062606240.jpg";
        btnLoadImage.setOnClickListener(v->
        {
            new Handle().execute(link);
        });

        btnActivity2.setOnClickListener(v->startActivity(new Intent(MainActivity.this, ReadContentActivity.class)));
    }

    private class Handle extends AsyncTask<String, Void, Bitmap>
    {

        Bitmap bitmap;
        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgDisplay.setImageBitmap(bitmap);
        }
    }
}