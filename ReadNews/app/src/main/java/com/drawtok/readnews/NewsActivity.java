package com.drawtok.readnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        webView = findViewById(R.id.direct_news_page);
        Intent intent = getIntent();
        String link = intent.getStringExtra("linkNews");

        webView.loadUrl(link);

        webView.setWebViewClient(new WebViewClient());
    }
}