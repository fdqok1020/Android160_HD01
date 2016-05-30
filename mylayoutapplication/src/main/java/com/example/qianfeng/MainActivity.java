package com.example.qianfeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.btn1);
    }
    public void click(View view){
        switch (view.getId()){
            case R.id.btn1:
                Intent intent = new Intent(this,MyLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                Intent intent1 = new Intent(this,MyLayoutDemoActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
