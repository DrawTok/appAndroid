package com.drawtok.youtubeapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String API_KEY = "AIzaSyAPYPx5iZoYQ8yxlAAhp2islHdRmhRXo_U";
    String ID_PLAY_LIST = "PLaOXx_DB8WFF1PMjVk_X-IjYfJjWi6Vpr";
    String urlGetJSON = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="
            +ID_PLAY_LIST+"&key="+API_KEY;
    ArrayList<Video> videoArrayList;
    ListView listView;
    VideoYoutubeAdapter adapter;
    /*int REQUEST_VIDEO = 1211;
    YouTubePlayerView youTubePlayerView;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        videoArrayList = new ArrayList<>();
        adapter = new VideoYoutubeAdapter(MainActivity.this, R.layout.row_video, videoArrayList);
        listView.setAdapter(adapter);
        /*youTubePlayerView = findViewById(R.id.display_youtube);
        youTubePlayerView.initialize(API_KEY, this);*/
        getJSONYoutube(urlGetJSON);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PlayVideoActivity.class);
                intent.putExtra("idVideoYoutube", videoArrayList.get(position).getIdVideo());
                startActivity(intent);
            }
        });
    }

    private void getJSONYoutube(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            String title = "", urlImage = "", videoID = "";
                            for(int i = 0; i < jsonItems.length(); i++)
                            {
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                title = jsonSnippet.getString("title");
                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                                urlImage = jsonMedium.getString("url");
                                JSONObject jsonResourceID = jsonSnippet.getJSONObject("resourceId");
                                videoID = jsonResourceID.getString("videoId");
                                videoArrayList.add(new Video(title, urlImage, videoID));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObject);
    }

   /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_VIDEO)
        {
            youTubePlayerView.initialize(API_KEY, this);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo("SkLVK4B5rqs");
        youTubePlayer.play();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            if (youTubeInitializationResult.isUserRecoverableError()) {
                youTubeInitializationResult.getErrorDialog(MainActivity.this, REQUEST_VIDEO);
            } else {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
    }*/
}