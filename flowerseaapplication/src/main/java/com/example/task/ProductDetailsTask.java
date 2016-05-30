package com.example.task;

import android.os.AsyncTask;

import com.example.bean.ProductDetails;
import com.example.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/5/28.
 */
public class ProductDetailsTask extends AsyncTask<String,Void,String> {


    private ProductDetails details;
    private ProductDetailsInterface face;

    public ProductDetailsTask(ProductDetailsInterface face) {
        this.face = face;
    }

    @Override
    protected String doInBackground(String... params) {
        String json = HttpUtils.getJsonWithPath(params[0]);
        Gson gson = new Gson();
        details = gson.fromJson(json, ProductDetails.class);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        face.onSuccess(details);
        super.onPostExecute(s);
    }
}
