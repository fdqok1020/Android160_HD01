package com.example.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.frg.GridFragment;
import com.example.frg.ListFragment;
import com.example.qianfeng.R;

public class CategoryProductActivity extends AppCompatActivity {

    //ui
    private FrameLayout frameLayout;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private int x = 1;
    private GridFragment gridFragment;
    private ListFragment listFragment;
    private Bundle bundle;
    private ImageView logo;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);
        initView();
        bundle = new Bundle();
        int homeId = getIntent().getIntExtra("homeId", 0);
        bundle.putInt("homeId", homeId);
        int subId = getIntent().getIntExtra("subId", 0);
        bundle.putInt("subId",subId);
        //设置一个值进行判断 如果m = 2 在跳转到搜索页面后点击回退的按钮是销毁当前的页面 不是调回主页
        int m = 2;
        bundle.putInt("m",m);
        transaction = manager.beginTransaction();
        gridFragment = GridFragment.newInstance();
        listFragment = ListFragment.newInstance();
        gridFragment.setArguments(bundle);
        transaction.replace(R.id.fl_layout, gridFragment);
        transaction.commit();
    }

    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.fl_layout);
        manager = getSupportFragmentManager();
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        logo = (ImageView) toolBar.findViewById(R.id.iv_search);
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
            case R.id.back:
                finish();
                break;
        }

    }
}
