package com.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
        //UI
   private ImageView ivLevel1,ivLevel2;
    private RelativeLayout  rl1,rl2;
    private  int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {

        ivLevel1= (ImageView) findViewById(R.id.im_pic1);
        ivLevel2 = (ImageView) findViewById(R.id.im_pic2);
        rl1 = (RelativeLayout) findViewById(R.id.rl_2);
        rl2= (RelativeLayout) findViewById(R.id.rl_3);
    }
    public  void click(View v){
        switch(v.getId()){
            case R.id.im_pic1:
                if(++i%2 !=0){
                    RotateAnimation roate = new RotateAnimation(0,180,rl1.getWidth()/2,rl1.getHeight());
                    roate.setDuration(2000);
                    roate.setFillAfter(true);
                    RotateAnimation roate1 = new RotateAnimation(0,180,rl2.getWidth()/2,rl2.getHeight());
                    roate1.setDuration(3000);
                    roate1.setFillAfter(true);
                    rl1.startAnimation(roate);
                    rl2.startAnimation(roate1);

                }else{
                    RotateAnimation roate = new RotateAnimation(180,360,rl1.getWidth()/2,rl1.getHeight());
                    roate.setDuration(2000);
                    roate.setFillAfter(true);

                    rl1.startAnimation(roate);

                }


                break;
            case R.id.im_pic2:

                if(++i%2==0){
                    RotateAnimation roate3 = new RotateAnimation(0,180,rl2.getWidth()/2,rl2.getHeight());
                    roate3.setDuration(3000);
                    roate3.setFillAfter(true);
                    rl2.startAnimation(roate3);
                }else{
                    RotateAnimation roate3 = new RotateAnimation(180,360,rl2.getWidth()/2,rl2.getHeight());
                    roate3.setDuration(3000);
                    roate3.setFillAfter(true);
                    rl2.startAnimation(roate3);

                }

                break;


        }

    }
}
