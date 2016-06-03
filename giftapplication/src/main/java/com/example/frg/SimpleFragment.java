package com.example.frg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.adapter.GuideTabPagerAadpter;
import com.example.constant.HttpConstant;
import com.example.qianfeng.giftapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {
    @BindView(R.id.home_table_strip)
    PagerSlidingTabStrip tabStrip;
    @BindView(R.id.home_view_pager)
    ViewPager mViewPager;
    private List<Fragment> data;
    private GuideTabPagerAadpter adapter;

    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment newInstance() {

        Bundle args = new Bundle();

        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        ButterKnife.bind(this,view);
        data = new ArrayList<>();
        initFragment();
        //创建适配器
        adapter = new GuideTabPagerAadpter(getFragmentManager(), data, HttpConstant.urlInfos);
        //绑定
        this.mViewPager.setAdapter(adapter);
        //联合指示器和ViewPager
        this.tabStrip.setViewPager(mViewPager);
        return view;
    }

    //初始化fragment
    private void initFragment() {
        for (int i = 0; i < HttpConstant.urlInfos.size(); i++) {
            if (i == 0){
            data.add(GuideFragment.newInstance(HttpConstant.urlInfos.get(i)));
            } else {
                data.add(GuideBaseFragment.newInstance(HttpConstant.urlInfos.get(i)));
            }
        }
    }

}
