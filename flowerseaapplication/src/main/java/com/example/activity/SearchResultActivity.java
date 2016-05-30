package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frg.GridFragment;
import com.example.frg.ListFragment;
import com.example.qianfeng.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchResultActivity extends AppCompatActivity {
    //UI
    private Toolbar mToolbar;
    private TextView tv;
    private String name;
    private FrameLayout frameLayout;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private int x = 1;
    private GridFragment gridFragment;
    private ListFragment listFragment;
    private Bundle bundle;
    private ImageView logo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        name = getIntent().getStringExtra("name");
        initView();
        tv.setText(name);
        bundle = new Bundle();
        try {
            String encode = URLEncoder.encode(name, "UTF-8");
            int m = 1;
            bundle.putInt("m",m);
            bundle.putString("path", encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        transaction = manager.beginTransaction();
        gridFragment = GridFragment.newInstance();
        listFragment = ListFragment.newInstance();
        gridFragment.setArguments(bundle);
        transaction.replace(R.id.fl_layout, gridFragment);
        transaction.commit();

    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tv = (TextView) mToolbar.findViewById(R.id.tv_title);
        setSupportActionBar(mToolbar);
        frameLayout = (FrameLayout) findViewById(R.id.fl_layout);
        manager = getSupportFragmentManager();
        logo = (ImageView) mToolbar.findViewById(R.id.iv_search);
    }

    public void back(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                ++x;
                if (x % 2 == 0) {
                    transaction = manager.beginTransaction();
                    listFragment.setArguments(bundle);
                    transaction.replace(R.id.fl_layout, listFragment);
                    transaction.commit();
                    logo.setImageResource(R.drawable.menu);
                } else {
                    transaction = manager.beginTransaction();
                    gridFragment.setArguments(bundle);
                    transaction.replace(R.id.fl_layout, gridFragment);
                    transaction.commit();
                    logo.setImageResource(R.drawable.category);
                }
                break;
        }
    }
}
