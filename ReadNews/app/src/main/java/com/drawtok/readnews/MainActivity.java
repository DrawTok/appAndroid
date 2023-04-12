package com.drawtok.readnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Bitmap imgNew;
    ListView lsContent;
    ArrayList<News> arrayNews;
    AdapterNews adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lsContent = findViewById(R.id.lv_content);
        arrayNews = new ArrayList<>();
        adapter = new AdapterNews(this, R.layout.layout_item_news, arrayNews);
        lsContent.setAdapter(adapter);
        new HandleData().execute("https://vnexpress.net/rss/tin-noi-bat.rss");
        lsContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("linkNews", arrayNews.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    private class HandleData extends AsyncTask<String, Void, String>
    {
        StringBuilder content = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new
                        InputStreamReader(url.openConnection().getInputStream());
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

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListDescription = document.getElementsByTagName("description");
            String title = "", link = "", time = "", imgLink = "";
            for(int i = 0; i <nodeList.getLength(); i++)
            {
                String cdata = nodeListDescription.item(i+1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                if(matcher.find())
                {
                    imgLink = matcher.group(1);
                }
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                time = parser.getValue(element, "pubDate");
                link = parser.getValue(element, "link");
                arrayNews.add(new News(imgLink, title, time, link));
            }
            adapter.notifyDataSetChanged();
        }
    }
    /*private class HandleImage extends AsyncTask<String, Void, Bitmap>
    {
        Bitmap bitmap;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
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
        }
    }*/
}