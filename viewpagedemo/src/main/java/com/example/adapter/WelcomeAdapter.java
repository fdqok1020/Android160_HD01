package com.example.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class WelcomeAdapter extends PagerAdapter {

    private List<ImageView> data;

    public WelcomeAdapter(List<ImageView> data) {
        this.data = data;
    }


    //返回ViewPager可以滑动的页面总数
    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    //返回当前也面对要进入的页面是否是同一个对象
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //将页面加入到ViewPager
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(data.get(position));
        return data.get(position);
    }
//删除已经划出去的页面


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(data.get(position));
    }
}
