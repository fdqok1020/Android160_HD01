package com.example.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class WebViewPageActivity extends AppCompatActivity {

    private WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_page);
        String url = getIntent().getStringExtra("url");

        //ui控件
        wb= (WebView) findViewById(R.id.webView);
        wb.loadUrl(url);

    }
}
