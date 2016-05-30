package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.qianfeng.R;

public class SettingsActivity extends AppCompatActivity {
    //ui
    private RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        rl = (RelativeLayout) findViewById(R.id.rl_line);
    }
    public void click(View view){

        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.rl_line:
                Intent intent = new Intent(this,OrderActivity.class);
                startActivity(intent);
                break;
        }


    }
}
