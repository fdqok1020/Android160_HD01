package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adapter.DiscoverAdapter;
import com.example.bean.HeadView;
import com.example.contanst.HttpURL;
import com.example.qianfeng.R;
import com.example.task.DiscoverTask;

import java.util.ArrayList;
import java.util.List;

public class DiscoverActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    //初始化ui
    private ListView disList;
    //适配器
    private DiscoverAdapter adapter;
    private List<HeadView.DataBean.TopicsBean> data;
    //异步任务
    private DiscoverTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        initView();
        data = new ArrayList<>();
        adapter = new DiscoverAdapter(this, data);
        task = new DiscoverTask(adapter);
        //绑定适配器
        disList.setAdapter(adapter);
        //开启异步任务
        task.execute(HttpURL.HEADVIEW_PATH);
        disList.setOnItemClickListener(this);
    }
    private void initView() {
        disList = (ListView) findViewById(R.id.dis_list);

    }


    public void click(View view){
       finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int discoverId = data.get(position).getId();
        String path = data.get(position).getImage();
        Intent intent = new Intent(this,DiscoverProductActivity.class);
        intent.putExtra("id",discoverId);
        intent.putExtra("path",path);
        startActivity(intent);

    }
}
