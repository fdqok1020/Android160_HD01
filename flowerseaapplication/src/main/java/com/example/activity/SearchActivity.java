package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.DiaryAdapter;
import com.example.bean.Diary;
import com.example.db.DBHelper;
import com.example.db.DiaryDao;
import com.example.qianfeng.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    //UI
    private ListView historyList;
    private EditText historyText;
    //数据源
    private List<Diary> data = new ArrayList<>();
    //适配器
    private String str;
    private DiaryAdapter adapter;
    private TextView tv;
    private DiaryDao dao;
    private List<Diary> newList;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        DBHelper helper = new DBHelper(this);
        dao = new DiaryDao(helper);
        data = dao.queryAll();
        adapter = new DiaryAdapter(this, data);
        historyList.setAdapter(adapter);


    }


    private void initView() {
        historyList = (ListView) findViewById(R.id.search_list);
        historyText = (EditText) findViewById(R.id.ed_search);
        data = new ArrayList<>();
        tv = (TextView) findViewById(R.id.tv_search);

    }

    @Override
    protected void onResume() {

        data.clear();
        newList = new ArrayList();
        newList = dao.queryAll();
        data.addAll(newList);
        adapter.notifyDataSetChanged();
        //对文本输入框进行监听
        historyText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if("".equals(historyText.getText().toString())){
                    tv.setText("取消");
                }else{
                    tv.setText("搜索");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        super.onResume();
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_del:
                for (int i = 0; i < data.size(); i++) {
                    String content = data.get(i).getContent();
                    if (content != null) {
                        dao.del(content);
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_search:
                //点击的时候进行判断 为搜索时跳转  为取消的时候销毁当前的页面
                if("搜索".equals(tv.getText().toString())){
                    str = historyText.getText().toString();
                    Diary diary = new Diary();
                    diary.setContent(str);
                    dao.add(diary);
                    Intent intent = new Intent(this, SearchResultActivity.class);
                    intent.putExtra("name", str);
                    startActivity(intent);
                }else if("取消".equals(tv.getText().toString())){
                    finish();
                }
                break;

        }
    }


}
