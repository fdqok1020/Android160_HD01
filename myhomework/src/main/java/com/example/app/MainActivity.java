package com.example.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.frg.JokeFragment;
import com.example.frg.NewsFragment;
import com.example.frg.WeatherFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    //ui
    private RadioGroup radioGroup;

    //Fragment
    //Fragment
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private NewsFragment newsFragment;
    private JokeFragment jokeFragment;
    private WeatherFragment weatherFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.rg_btn);
        radioGroup.setOnCheckedChangeListener(this);
        manager =getSupportFragmentManager();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction =manager.beginTransaction();
        switch (checkedId){
            case R.id.btn_news:
                if(newsFragment==null){
                    newsFragment = NewsFragment.newInstance();
                    transaction.add(R.id.rl_main,newsFragment,"news");
                    if(jokeFragment!=null){
                        transaction.hide(jokeFragment);
                    }
                    if(weatherFragment!=null){
                        transaction.hide(weatherFragment);
                    }
                }else{
                    transaction.show(newsFragment);
                    if(jokeFragment!=null){
                        transaction.hide(jokeFragment);
                    }
                    if(weatherFragment!=null){
                        transaction.hide(weatherFragment);
                    }
                }
                break;
            case R.id.btn_joke:
                if(jokeFragment==null){
                   jokeFragment = JokeFragment.newInstance();
                    transaction.add(R.id.rl_main,jokeFragment,"joke");
                    if(newsFragment!=null){
                        transaction.hide(newsFragment);
                    }
                    if(weatherFragment!=null){
                        transaction.hide(weatherFragment);
                    }
                }else{
                    transaction.show(jokeFragment);
                    if(newsFragment!=null){
                        transaction.hide(newsFragment);
                    }
                    if(weatherFragment!=null){
                        transaction.hide(weatherFragment);
                    }
                }
                break;
            case R.id.btn_weather:
                if(weatherFragment==null){
                    weatherFragment =WeatherFragment.newInstance();
                    transaction.add(R.id.rl_main,weatherFragment,"weather");
                    if(newsFragment!=null){
                        transaction.hide(newsFragment);
                    }
                    if(jokeFragment!=null){
                        transaction.hide(jokeFragment);
                    }
                }else{
                    transaction.show(weatherFragment);
                    if(newsFragment!=null){
                        transaction.hide(newsFragment);
                    }
                    if(jokeFragment!=null){
                        transaction.hide(jokeFragment);
                    }
                }



                break;



        }
        transaction.commit();
    }
}
