package com.example.frg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.NewsTabPagerAadpter;
import com.example.app.R;
import com.example.constant.Constant;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    //UI
    private ViewPager vp;

    private TabPageIndicator indicator;

    //适配器
    private NewsTabPagerAadpter adapter;

    private List<Fragment> data;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        data = new ArrayList<>();
        initFragment();
        //创建适配器
        adapter = new NewsTabPagerAadpter(getFragmentManager(), data,Constant.urlInfos);
        //绑定
        this.vp.setAdapter(adapter);
        //联合指示器和ViewPager
     this.indicator.setViewPager(vp);
        return view;
    }
    /**
     * 初始化UI
     */
    private void initView(View view) {
        vp = (ViewPager) view.findViewById(R.id.vp);
        this.indicator = (TabPageIndicator) view.findViewById(R.id.indicator);

    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        for (int i = 0; i < Constant.urlInfos.size(); i++) {
            data.add(SimpleFragment.newInstance(Constant.urlInfos.get(i)));
        }
    }


    public NewsFragment() {
    }

    public static NewsFragment newInstance() {

        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
