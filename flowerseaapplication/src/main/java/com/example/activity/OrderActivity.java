package com.example.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.frg.LoginFragment;
import com.example.frg.RegisterFragment;
import com.example.qianfeng.R;

public class OrderActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    //UI
    private Toolbar mToolbar;
    private RadioGroup group;
    private RelativeLayout rl;
    //设置fragment管理器
    private FragmentManager manager;
    //声明一个Fragmenent事务
    private FragmentTransaction transaction;
    private LoginFragment fragment;
    private RegisterFragment fragment1;
    private ImageView mIvBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initiew();
        setSupportActionBar(mToolbar);
        //得到管理器
        manager = getSupportFragmentManager();
        //默认第一个按钮呗选中
        RadioButton childAt = (RadioButton) group.getChildAt(0);
        childAt.setChecked(true);
        onCheckedChanged(group,R.id.btn_login);
        group.setOnCheckedChangeListener(this);
    }

    private void initiew() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        group = (RadioGroup) mToolbar.findViewById(R.id.btn_rg);
        mIvBack = (ImageView) mToolbar.findViewById(R.id.back);
        rl = (RelativeLayout) findViewById(R.id.rl_frg);
    }
    public void click (View view){
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //得到事务
        transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.btn_login:
                if (fragment == null) {
                    fragment = LoginFragment.newInstance();
                    //动态加载fragment
                    transaction.add(R.id.rl_frg, fragment, "login");
                    if (fragment1 != null) {
                        transaction.hide(fragment1);
                    }
                } else {
                    transaction.show(fragment);
                    if (fragment1 != null) {
                        transaction.hide(fragment1);
                    }
                }
                break;
            case R.id.btn_register:

                if (fragment1 == null) {
                    fragment1 = RegisterFragment.newInstance();
                    transaction.add(R.id.rl_frg, fragment1, "register");
                    if (fragment != null) {
                        transaction.hide(fragment);
                    }
                } else {
                    transaction.show(fragment1);
                    if (fragment != null) {
                        transaction.hide(fragment);
                    }
                }

                break;
        }
        transaction.commit();
    }

}

