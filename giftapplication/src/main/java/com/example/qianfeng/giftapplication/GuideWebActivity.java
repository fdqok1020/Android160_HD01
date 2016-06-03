package com.example.qianfeng.giftapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class GuideWebActivity extends AppCompatActivity {
        private WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_web);
        String url = getIntent().getStringExtra("url");
        wb= (WebView) findViewById(R.id.guide_web_view);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.loadUrl(url);
    }
}
