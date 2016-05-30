package com.example.task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.adapter.NewsAdapter;
import com.example.bean.News;
import com.example.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/5/5.
 */
public class NewsTask extends AsyncTask<String, Void, String> {
    private NewsAdapter adapter;

    public NewsTask(NewsAdapter adapter) {

        this.adapter = adapter;

    }


    @Override
    protected String doInBackground(String... params) {
        String path = params[0];
        String json = HttpUtils.getJsonWithPath(path);
        try {
//            Log.i("TAG","----"+json);
            JSONObject obj2 = new JSONObject(json);
            JSONObject result = obj2.getJSONObject("result");
            JSONArray array = result.getJSONArray("list");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String firstImg = obj.getString("firstImg");
                String source = obj.getString("source");
                String title = obj.getString("title");
                Log.i("TAG","++++"+firstImg);
                News news = new News(title, source, firstImg);
                adapter.getData().add(news);
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
    }
}
