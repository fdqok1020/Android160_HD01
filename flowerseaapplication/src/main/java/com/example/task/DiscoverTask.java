package com.example.task;

import android.os.AsyncTask;

import com.example.adapter.DiscoverAdapter;
import com.example.bean.HeadView;
import com.example.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class DiscoverTask extends AsyncTask<String,Void,String> {
    private DiscoverAdapter adapter;

    public DiscoverTask(DiscoverAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected String doInBackground(String... params) {
        String path = HttpUtils.getJsonWithPath(params[0]);
        Gson gson = new Gson();
        HeadView head = gson.fromJson(path,HeadView.class);
        List<HeadView.DataBean.TopicsBean> list = head.getData().getTopics();
        adapter.getData().addAll(list);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        adapter.notifyDataSetChanged();
    }
}
