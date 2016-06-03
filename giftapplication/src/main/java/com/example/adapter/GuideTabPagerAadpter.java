package com.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.constant.HttpConstant;

import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class GuideTabPagerAadpter extends FragmentStatePagerAdapter {

    private List<Fragment> data;

    private List<HttpConstant.UrlInfo> urlInfos;

    public GuideTabPagerAadpter(FragmentManager fm, List<Fragment> data, List<HttpConstant.UrlInfo> urlInfos) {
        super(fm);
        this.data=data;
        this.urlInfos=urlInfos;
    }

    public List<Fragment> getData() {
        return data;
    }

    @Override
    public Fragment getItem(int position) {
        return this.data!=null?this.data.get(position):null;
    }

    @Override
    public int getCount() {
        return this.data!=null?data.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.urlInfos.get(position).getTitle();
    }
}
