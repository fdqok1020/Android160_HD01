package com.example.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.frg.BaseDiscoverFragment;
import com.example.qianfeng.R;

public class DiscoverProductActivity extends AppCompatActivity {
    //  ui
    private FrameLayout frameLayout;
    private FragmentManager manager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_product);
        int id = getIntent().getIntExtra("id", 0);
        String path = getIntent().getStringExtra("path");
        initView();
        transaction = manager.beginTransaction();
        BaseDiscoverFragment disFragment = BaseDiscoverFragment.newInstance();
        //Activity向fragment中传递数据要使用bundle  并调用setArguments方法
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        bundle.putString("path",path);
        disFragment.setArguments(bundle);
        transaction.replace(R.id.fl_layout, disFragment);
        transaction.commit();

    }
    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.fl_layout);
        manager = getSupportFragmentManager();
    }
    public void click(View view){
        finish();
    }
}
