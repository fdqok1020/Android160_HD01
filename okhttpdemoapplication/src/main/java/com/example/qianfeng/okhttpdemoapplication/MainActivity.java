package com.example.qianfeng.okhttpdemoapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPost;
    OkHttpClient okHttp = new OkHttpClient();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private String TAG= "+xxxxxxxxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPost = (Button) findViewById(R.id.btn_post);
        btnPost.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FormBody fromBody = new FormBody.Builder()
                .add("user","jihuanqing")
                .build();
        Request request= new Request.Builder()
                .post(fromBody)
                .url("http://10.6.155.92:8080/WebServer/login.do")
                .build();
        okHttp.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());

            }
        });

    }


}
