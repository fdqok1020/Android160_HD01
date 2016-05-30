package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.app.activity.AddActivity;
import com.example.app.activity.BaseActivity;
import com.example.app.activity.DiaryActivity;
import com.example.app.activity.EdlitActivity;
import com.example.app.adapter.DiaryAdapter;
import com.example.app.constast.bean.Diary;
import com.example.app.db.dao.DiaryDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    //控件
    private ListView lvDiary;
    private List<Diary> diary;
    //适配器
    private DiaryAdapter adapter;
    private DiaryDao dao;
    private List<Diary> newList = new ArrayList<>();


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDiary = (ListView) findViewById(R.id.lv_message);
        dao = new DiaryDao(helper);
        diary = dao.queryAll();
        adapter = new DiaryAdapter(diary,this);
        //绑定
        lvDiary.setAdapter(adapter);
        lvDiary.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        diary.clear();
        newList = dao.queryAll();
        diary.addAll(newList);
        adapter.notifyDataSetChanged();

    }

    public void click(View view) {

        switch (view.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_edlit:
                Intent intent1 = new Intent(this, EdlitActivity.class);
                startActivity(intent1);

        }

            adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intentt3 = new Intent(getApplicationContext(), DiaryActivity.class);
        Diary diy =diary.get(position);
        Log.i("TAG```", String.valueOf(diy.getId()));
        intentt3.putExtra("diary",diy);
        startActivity(intentt3);
    }

}
