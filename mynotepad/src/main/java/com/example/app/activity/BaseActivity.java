package com.example.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.app.db.DBHelper;

/**
 * Created by Administrator on 2016/4/30.
 */
public class BaseActivity extends AppCompatActivity {


    protected DBHelper helper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new DBHelper(this);
    }
}
