package com.example.task;

import android.os.AsyncTask;

import com.example.bean.HeadView;
import com.example.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class HeadTask extends AsyncTask<String, Void, String> {
    private HeadViewInterface headViewInterface;
    public HeadTask( HeadViewInterface headViewInterface) {
        this.headViewInterface = headViewInterface;
    }

    List<HeadView.DataBean.TopicsBean> list = new ArrayList<>();

    @Override
    protected String doInBackground(String... params) {

        String json = HttpUtils.getJsonWithPath(params[0]);
        Gson gson = new Gson();
        HeadView head = gson.fromJson(json, HeadView.class);
        list = head.getData().getTopics();
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        headViewInterface.onSuccess(list);

    }

}
