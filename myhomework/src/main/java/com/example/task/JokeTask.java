package com.example.task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.adapter.JokeAdapter;
import com.example.bean.Joke;
import com.example.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/5/4.
 */
public class JokeTask extends AsyncTask<String, Void, String> {
    private JokeAdapter adapter;

    public JokeTask(JokeAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected String doInBackground(String... params) {
        String path = params[0];
        String json = HttpUtils.getJsonWithPath(path);
        try {

            Log.i("TAG", "=======" + json);
            JSONObject obj = new JSONObject(json);
            JSONArray array = obj.getJSONArray("段子");
            for (int i = 0;i<array.length();i++){
                JSONObject obj1 = array.getJSONObject(i);
                String digest = obj1.getString("digest");
                String source = obj1.getString("source");
                String title = obj1.getString("title");
                Joke joke = new Joke(digest,source,title);
                adapter.getData().add(joke);
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
