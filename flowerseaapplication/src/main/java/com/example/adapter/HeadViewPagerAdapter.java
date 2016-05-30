package com.example.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21.
 */
public class HeadViewPagerAdapter extends PagerAdapter {
    private List<View> lv;

    public HeadViewPagerAdapter(List<View> lv) {
        this.lv = lv;
    }



    @Override
    public int getCount() {
        return lv!=null?lv.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(lv.get(position));
        return lv.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(lv.get(position));
    }
}
