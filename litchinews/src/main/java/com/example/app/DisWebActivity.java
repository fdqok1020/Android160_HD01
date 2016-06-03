package com.example.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class DisWebActivity extends AppCompatActivity {
        private WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_web);
        String url = getIntent().getStringExtra("url");
        wb= (WebView) findViewById(R.id.webView);
        wb.loadUrl(url);

    }
}
