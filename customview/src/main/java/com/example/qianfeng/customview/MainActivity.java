package com.example.qianfeng.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button btn_plane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_plane = (Button) findViewById(R.id.btn_plane);
    }
    public void  click(View view){
        Intent intent =null;
        switch (view.getId()){
            case R.id.btn_plane:
                intent = new Intent(this,PlaneActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_danmu:
                intent = new Intent(this,DanMuActivity.class);
                startActivity(intent);
                break;
        }


    }
}
