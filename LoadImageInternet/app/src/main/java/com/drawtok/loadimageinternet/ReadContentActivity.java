package com.drawtok.loadimageinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ReadContentActivity extends AppCompatActivity {

    Button btnLoadContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_content);

        btnLoadContent = findViewById(R.id.btn_loadContent);

        btnLoadContent.setOnClickListener(v-> new HandleContentData().execute("https://www.facebook.com/lethanhhoa03"));
    }

    private class HandleContentData extends AsyncTask<String, Void, String>
    {
        // dạng chuỗi, đọc để đổ dữ liệu vào
        StringBuilder stringBuilder = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                // Mở kết nối đường dẫn
                URLConnection connection = url.openConnection();
                // lấy dữ liệu từ đường dẫn
                InputStream inputStream = connection.getInputStream();
                // đọc dữ liệu ra
                InputStreamReader reader = new InputStreamReader(inputStream);
                // đọc dữ liệu liên tục từ inputStreamReader
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(line+"\n");
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(ReadContentActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}