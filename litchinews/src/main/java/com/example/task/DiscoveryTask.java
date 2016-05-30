package com.example.task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.adapter.DiscoveryAdapter;
import com.example.bean.DiscoveryList;
import com.example.utils.HttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/5/6.
 */
public class DiscoveryTask extends AsyncTask<String, Void, String> {
    private DiscoveryAdapter adapter;
    private PullToRefreshListView ptflv;

    public DiscoveryTask(DiscoveryAdapter adapter,PullToRefreshListView ptflv) {
        this.adapter = adapter;
        this.ptflv = ptflv;
    }

    @Override
    protected String doInBackground(String... params) {
        String path = params[0];
        String josn = HttpUtils.getJsonWithPath(path);
        try {
            JSONObject obj = new JSONObject(josn);
            JSONObject data = obj.getJSONObject("Data");
            JSONArray list = data.getJSONArray("List");
            for (int i = 0; i < list.length(); i++) {
                JSONObject obj2 = list.getJSONObject(i);
                JSONObject obj3 = obj2.getJSONObject("Data");
                String photo = obj3.getString("Photo");
                String href = obj3.getString("Href");
                DiscoveryList dis = new DiscoveryList(photo, href);
                Log.i("TAG", "+++++++" + photo);
                adapter.getData().add(dis);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        adapter.notifyDataSetChanged();
        ptflv.onRefreshComplete();

    }
}
