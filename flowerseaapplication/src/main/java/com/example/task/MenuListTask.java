package com.example.task;

import android.os.AsyncTask;

import com.example.adapter.MenuListAdapter;
import com.example.bean.Category;
import com.example.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 */
public class MenuListTask extends AsyncTask<String,Void,String> {
    private MenuListAdapter adapter;
    private MenuListData menuListData;
    private List<Category.DataBean.CategoriesBean.SubBean> sub;

    public MenuListTask(MenuListAdapter adapter,MenuListData menuListData) {
        this.adapter = adapter;
        this.menuListData = menuListData;
    }

    @Override
    protected String doInBackground(String... params) {
        String json = HttpUtils.getJsonWithPath(params[0]);
        Gson gson = new Gson();
        Category category = gson.fromJson(json,Category.class);
        List<Category.DataBean.CategoriesBean> data = new ArrayList<>();
        data = category.getData().getCategories();
        sub = data.get(0).getSub();
        adapter.getData().addAll(data);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        menuListData.getData(sub);
        super.onPostExecute(s);
        adapter.notifyDataSetChanged();
    }
}
