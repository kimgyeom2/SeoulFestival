package com.gy25m.seoulfestival;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PlaceUrlActivity extends AppCompatActivity {
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_url);
        wv=findViewById(R.id.wv);
        wv.setWebViewClient(new WebViewClient());
        wv.setWebChromeClient(new WebChromeClient());

        String place=new Intent().getStringExtra("url");
        wv.loadUrl("https://map.kakao.com/link/search/"+place);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (wv.canGoBack())wv.goBack();
        else super.onBackPressed();
    }
}