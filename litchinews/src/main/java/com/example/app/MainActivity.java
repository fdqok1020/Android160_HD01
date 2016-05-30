package com.example.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.frg.DiscoveryFragment;
import com.example.frg.MyFragment;
import com.example.frg.NewsFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    //ui
    private RadioGroup rgButton;

    //fragment相关的
    private FragmentManager manager;
    private FragmentTransaction transaction;
    //三个Fragment
    private NewsFragment newFragment;
    private DiscoveryFragment disFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        manager = getSupportFragmentManager();
        //选择中新闻
        RadioButton childAt = (RadioButton) rgButton.getChildAt(0);
        childAt.setChecked(true);

    }

    private void initView() {
        rgButton = (RadioGroup) findViewById(R.id.rg_buttom);
        //设置监听事件
        rgButton.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction=manager.beginTransaction();
        switch (checkedId)
        {
            case  R.id.rb_news:
                if(newFragment==null){
                    newFragment=NewsFragment.newInstance();
                    transaction.add(R.id.ll_main,newFragment,"news");
                    if(disFragment!=null){
                        transaction.hide(disFragment);
                    }
                    if(myFragment!=null){
                        transaction.hide(myFragment);
                    }
                }else {
                    transaction.show(newFragment);
                    if(disFragment!=null){
                        transaction.hide(disFragment);
                    }
                    if(myFragment!=null){
                        transaction.hide(myFragment);
                    }
                }
                break;
            case R.id.rb_dis:
                if(disFragment==null){
                    disFragment=DiscoveryFragment.newInstance();
                    transaction.add(R.id.ll_main,disFragment,"discovery");
                    if(newFragment!=null){
                        transaction.hide(newFragment);
                    }
                    if(myFragment!=null){
                        transaction.hide(myFragment);
                    }
                }else {
                    transaction.show(disFragment);
                    if(newFragment!=null){
                        transaction.hide(newFragment);
                    }
                    if(myFragment!=null){
                        transaction.hide(myFragment);
                    }
                }
                break;
            case  R.id.rb_my:
                if(myFragment==null){
                    myFragment=MyFragment.newInstance();
                    transaction.add(R.id.ll_main,myFragment,"my");
                    if(newFragment!=null){
                        transaction.hide(newFragment);
                    }
                    if(disFragment!=null){
                        transaction.hide(disFragment);
                    }
                }else {
                    transaction.show(myFragment);
                    if(newFragment!=null){
                        transaction.hide(newFragment);
                    }
                    if(disFragment!=null){
                        transaction.hide(disFragment);
                    }
                }
                break;
        }
        transaction.commit();
    }
}
