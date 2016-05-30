package com.example.task;

import android.os.AsyncTask;

import com.example.adapter.NewsAdapter;
import com.example.bean.News;
import com.example.frg.SimpleFragment;
import com.example.utils.HttpUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

public class NewsTask extends AsyncTask<String,Void,String> {
    private NewsAdapter adapter;
    private SimpleFragment fragment;
    private PullToRefreshListView lv;

    //头部文件
    private List<News.NewsBean.HeadPicsBean> headPics;

    public NewsTask(NewsAdapter adapter, PullToRefreshListView lv,SimpleFragment fragment) {
        this.adapter = adapter;
        this.lv=lv;
        this.fragment=fragment;
    }


    @Override
    protected String doInBackground(String... params) {
        String json = HttpUtils.getJsonWithPath(params[0]);
        Gson gson = new Gson();
        News news = gson.fromJson(json,News.class);
        headPics = news.getData().getHeadPics();
        //清空数据
        adapter.getData().clear();
        //添加到集合
        adapter.getData().addAll(news.getData().getList());
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        adapter.notifyDataSetChanged();
        //完成刷新
        lv.onRefreshComplete();
        //判断数据里面有没头部信息
        if(headPics.size()>0){
            //添加头
            fragment.addHeadView(headPics);
        }
    }
}
