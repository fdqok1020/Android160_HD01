package com.example.task;

import android.os.AsyncTask;

import com.example.adapter.CityAdapter;
import com.example.bean.City;
import com.example.utils.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/5/4.
 */
public class WeatherTask extends AsyncTask<String, Void, String> {
    private CityAdapter adapter;

    public WeatherTask(CityAdapter adapter) {

        this.adapter = adapter;

    }

    @Override
    protected String doInBackground(String... params) {

        String path = params[0];
        String json = HttpUtils.getJsonWithPath(path);

//        Log.i("TAG", "=======" + json);
        JSONObject obj = null;
        try {
            obj = new JSONObject(json);
            JSONObject obj1 = obj.getJSONObject("weatherinfo");
            String city = obj1.getString("city");
            String temp = obj1.getString("temp");
            String WD = obj1.getString("WD");
            String SD = obj1.getString("SD");
            String time = obj1.getString("time");
            String njd = obj1.getString("njd");
            City citys = new City(city, temp, WD, SD, time, njd);
            adapter.getData().add(citys);
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
