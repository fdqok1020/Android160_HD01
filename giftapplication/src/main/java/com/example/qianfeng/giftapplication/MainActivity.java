package com.example.qianfeng.giftapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.frg.CategoryFragment;
import com.example.frg.HotFragment;
import com.example.frg.MyFragment;
import com.example.frg.SimpleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    //ui
    @BindView(R.id.home_radio_group)
    RadioGroup mHomeRadioGroup;
    @BindView(R.id.home_fragment_fl)
    FrameLayout mFagmentFl;


    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        mHomeRadioGroup.setOnCheckedChangeListener(this);
        RadioButton childAt = (RadioButton) mHomeRadioGroup.getChildAt(0);
        childAt.setChecked(true);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.home_radio_button_guide:
//                GuideFragment guideFragment = GuideFragment.newInstance();
//                transaction.replace(R.id.home_fragment_fl, guideFragment);
                SimpleFragment simpleFragment = SimpleFragment.newInstance();
                transaction.replace(R.id.home_fragment_fl,simpleFragment);
                break;
            case R.id.home_radio_button_hot:
                HotFragment hotFragment = HotFragment.newInstance();
                transaction.replace(R.id.home_fragment_fl, hotFragment);
                break;
            case R.id.home_radio_button_category:
                CategoryFragment categoryFragment = CategoryFragment.newInstance();
                transaction.replace(R.id.home_fragment_fl, categoryFragment);
                break;
            case R.id.home_radio_button_my:
                MyFragment myFragment = MyFragment.newInstance();
                transaction.replace(R.id.home_fragment_fl, myFragment);
                break;
        }
        transaction.commit();
    }
}
