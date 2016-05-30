package com.example.task;

import android.os.AsyncTask;

import com.example.adapter.ListAdapter;
import com.example.bean.Product;
import com.example.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class ListTask extends AsyncTask<String, Void, String> {
    private ListAdapter adapter;

    public ListTask(ListAdapter adapter) {
        this.adapter = adapter;
    }

    //商品类
    private List<Product.DataBean.ProductsBean> list = new ArrayList<>();

    @Override
    protected String doInBackground(String... params) {
        String path = params[0];
        String json = HttpUtils.getJsonWithPath(path);
        Gson gson = new Gson();
        Product product = gson.fromJson(json, Product.class);
        list = product.getData().getProducts();
        adapter.getData().addAll(list);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        adapter.notifyDataSetChanged();
    }
}
