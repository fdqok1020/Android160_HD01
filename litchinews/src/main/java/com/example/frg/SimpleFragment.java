package com.example.frg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.adapter.NewsAdapter;
import com.example.adapter.vp.HeadViewPagerAdapter;
import com.example.app.R;
import com.example.app.WebActivity;
import com.example.bean.News;
import com.example.constant.Constant;
import com.example.task.NewsTask;
import com.example.utils.ImageLoader;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class SimpleFragment extends Fragment {


    public SimpleFragment() {
        // Required empty public constructor
    }

    public static SimpleFragment newInstance(Constant.UrlInfo info) {

        Bundle args = new Bundle();
        args.putSerializable("info", info);
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private PullToRefreshListView ptrlv;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        ptrlv = (PullToRefreshListView) view.findViewById(R.id.ptflv);
        //添加页面为空时候的有个进度条
        ProgressBar progressBar = new ProgressBar(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(params);
        ptrlv.setEmptyView(progressBar);

        Constant.UrlInfo info = (Constant.UrlInfo) getArguments().getSerializable("info");
        if (info.getId() == 0) {
            ///头条
            initNormal(info);
        } else if (info.getId() == 1) {
            //国内
            initNormal(info);
        } else if (info.getId() == 2) {
            //社会
            initNormal(info);
        } else if (info.getId() == 3) {
            //体育
            initNormal(info);
        } else if (info.getId() == 4) {
            //科技
            initNormal(info);
        } else if (info.getId() == 5) {
            //真相
            initNormal(info);
        }
        //添加事件
        ptrlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListView refreshableView = ptrlv.getRefreshableView();
                Log.i("TAG","选中的值为"+position);
                int count  =refreshableView.getHeaderViewsCount();

                if (count!=1){//不等于1说明有头部文件

                    position-=2;
                    Log.i("TAG","值为"+position);
                }else {
                    position-=1;
                }
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url",normalData.get(position).getData().getHref());
                startActivity(intent);

            }

        });



        return view;
    }

    private NewsAdapter normalAdapter;
    private List<News.NewsBean.ListBean> normalData;
    private NewsTask task;
    private int i=0;

    private void initNormal(final Constant.UrlInfo info) {
        normalData = new ArrayList<>();
        //创建适配器
        normalAdapter = new NewsAdapter(getActivity(), normalData);
        //绑定适配器
        ptrlv.setAdapter(normalAdapter);
        task = new NewsTask(normalAdapter, ptrlv, this);
        //开启异步任务
        task.execute(info.getUrl());
        //添加下拉刷新事件
        ptrlv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //下载数据
                task = new NewsTask(normalAdapter, ptrlv, SimpleFragment.this);
                task.execute(info.getUrl());
            }
        });

    }

    private HeadViewPagerAdapter adapter;
    private List<View> data;
    private ViewPager vpHeadView;
    private LinearLayout llDots;

    //添加头部文件
    public void addHeadView(final List<News.NewsBean.HeadPicsBean> headPics) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.listview_headview, null);
        vpHeadView = (ViewPager) view.findViewById(R.id.vp_head);
        data = new ArrayList<>();
        llDots = (LinearLayout) view.findViewById(R.id.ll_dots);
        //放入数据
        for (  int i = 0; i < headPics.size(); i++) {
            News.NewsBean.HeadPicsBean bean = headPics.get(i);
            View vpHead = getActivity().getLayoutInflater().inflate(R.layout.listview_head_viewpagre, null);
            TextView tv = (TextView) vpHead.findViewById(R.id.tv_title);
            ImageView iv = (ImageView) vpHead.findViewById(R.id.iv_headview);
            //数据映射
            ImageLoader.loaderImage(bean.getData().getPhoto(), iv);
            tv.setText(bean.getData().getTitle());
            data.add(vpHead);
            //添加点
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 0, 0, 0);
            imageView.setLayoutParams(params);
            //设置显示的图片
            imageView.setImageResource(R.drawable.selecter_dot);
            //设置为不可编辑
            imageView.setEnabled(true);
            //添加到集合
            llDots.addView(imageView);
            final int x = i;
            imageView.setTag(i);
            //添加事件
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    vpHeadView.setCurrentItem(pos);
                }
            });
//            vpHeadView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    Intent intent = new Intent(getActivity(), WebViewPageActivity.class);
//                    intent.putExtra("url",headPics.get(position).getData().getHref());
//                    startActivity(intent);
//
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
            vpHeadView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            //默认选中第一个
            llDots.getChildAt(0).setEnabled(false);
        }
        adapter = new HeadViewPagerAdapter(data);
        //绑定适配器
        vpHeadView.setAdapter(adapter);
        //添加头
        ListView refreshableView = ptrlv.getRefreshableView();
        //设置头部的宽和高 在XML文件中设置的无效 否则在页面中不会显示
        //必须使用 AbsListView.LayoutParams来设置宽高
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 360);
        view.setLayoutParams(params);
        int headViewCount = refreshableView.getHeaderViewsCount();
        if (headViewCount <= 1) {
            refreshableView.addHeaderView(view);

        }

        //添加事件
        this.vpHeadView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < llDots.getChildCount(); i++) {
                    //将所有的点都变成灰色
                    llDots.getChildAt(i).setEnabled(true);
                }
                //将当前选中的点变成红色
                llDots.getChildAt(position).setEnabled(false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
}
