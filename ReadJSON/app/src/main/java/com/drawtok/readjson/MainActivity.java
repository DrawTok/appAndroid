package com.drawtok.readjson;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    ImageView imgLanguage;
    String sFromOnPost;
    int check = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.tv_info);
        imgLanguage = findViewById(R.id.img_logo);
        new ReadJSON().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");
        imgLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (check)
                {
                    case 1:
                        getJSON("en");
                        imgLanguage.setImageResource(R.drawable.img);
                        check = 0;
                        break;
                    case 0:
                        getJSON("vn");
                        imgLanguage.setImageResource(R.drawable.vnflag);
                        check = 1;
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private class ReadJSON extends AsyncTask<String, Void, String>
    {
        StringBuilder content = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader =
                        new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            /*try {
                //JSON Object
                JSONObject object = new JSONObject(s);
                String monhoc = object.getString("monhoc");
                tvMonHoc.setText(monhoc);
            } catch (JSONException e) {
                e.printStackTrace();
            }*/

            //JSON Array
            /*try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("danhsach");
                for(int i = 0; i < array.length(); i++)
                {
                    JSONObject objectSubject = array.getJSONObject(i);
                    String subject = objectSubject.getString("khoahoc");
                    Toast.makeText(MainActivity.this, subject, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/

            sFromOnPost = s;

        }
    }

    private void getJSON(String language)
    {
        try {
            JSONObject object = new JSONObject(sFromOnPost);
            JSONObject objectLanguage = object.getJSONObject("language");
            JSONObject switchVn_En = objectLanguage.getJSONObject(language);
            String name = switchVn_En.getString("name");
            String address = switchVn_En.getString("address");
            String course1 = switchVn_En.getString("course1");
            String course2 = switchVn_En.getString("course2");
            String course3 = switchVn_En.getString("course3");
            tvInfo.setText(name+"\n"+address+"\n"+course1+"\n"+course2+"\n"+course3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}