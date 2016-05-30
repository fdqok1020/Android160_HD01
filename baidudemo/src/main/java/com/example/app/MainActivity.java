package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

public class MainActivity extends AppCompatActivity {

    MyApplication myApplication;
    private Button mLocationBtn;
    private TextView mShowTxt;
    private boolean isLocationSuccess;

    private BDLocation mBDloaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myApplication = (MyApplication) getApplication();
        //注册监听
        myApplication.getLocationClient().registerLocationListener(new MyLocationListener());
        //启动定位
        myApplication.getLocationClient().start();
        initView();
    }

    private void initView() {
        mLocationBtn = (Button) findViewById(R.id.location_btn);
        mShowTxt = (TextView) findViewById(R.id.location_show_txt);

        mLocationBtn.setVisibility(View.INVISIBLE);

        mLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocationSuccess) {
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    intent.putExtra("location_name",mBDloaction.getCity());
                    intent.putExtra("location_lat",mBDloaction.getLatitude());
                    intent.putExtra("location_lng",mBDloaction.getLongitude());
                    startActivity(intent);
                } else {
                    mLocationBtn.setVisibility(View.INVISIBLE);
                    myApplication.getLocationClient().requestLocation();
                }

            }
        });
    }

    public  void click(View view) {
        //重新定位，如果需要重新获取定位数据requestLocation
        myApplication.getLocationClient().requestLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //定位结束
        myApplication.getLocationClient().stop();
    }


    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            int locType = bdLocation.getLocType();
            if (61 == locType || 161 == locType) {
                mBDloaction = bdLocation;
                mShowTxt.setText(bdLocation.getCity());
                mLocationBtn.setText("选择");
                isLocationSuccess = true;
                mLocationBtn.setVisibility(View.VISIBLE);
            } else {
                mLocationBtn.setVisibility(View.VISIBLE);
                mLocationBtn.setText("重新定位");
                isLocationSuccess = false;
            }
        }
    }
}
