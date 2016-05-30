package com.example.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adapter.WelcomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private List<ImageView> data;
    private WelcomeAdapter adapter;
    private int[] images = {R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4, R.mipmap.pic1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = (ViewPager) findViewById(R.id.vp);
        creatPicture();
        //创建适配器
        adapter = new WelcomeAdapter(data);
        //绑定
        vp.setAdapter(adapter);

    }

    private void creatPicture() {
        data = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            //设置图片铺满屏幕
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //设置显示的图片
            imageView.setImageResource(images[i]);
            data.add(imageView);

        }

    }
}
