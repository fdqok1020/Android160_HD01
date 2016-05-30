package com.example.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.adapter.EdlitAdapter;
import com.example.app.constast.bean.Diary;
import com.example.app.db.dao.DiaryDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class EdlitActivity extends BaseActivity {
    private ListView lvEdlit;
    private EdlitAdapter adapter;
    private List<Diary> data;
    //记录选中的条数
    private int checkNum;
    ;
    private DiaryDao dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);
        //获取控件
        lvEdlit = (ListView) findViewById(R.id.lv_diary);
        data = new ArrayList<Diary>();
        dao = new DiaryDao(helper);
        initData();
        adapter = new EdlitAdapter(data, this);
        lvEdlit.setAdapter(adapter);

    }

    private void initData() {
        data = dao.queryAll();
    }

    public void click(View view) {
        switch (view.getId()) {
            //全选
            case R.id.btn_all_choice:
                for (int i = 0; i < data.size(); i++) {
                    EdlitAdapter.getIsSelected().put(i, true);
                }
                checkNum = data.size();
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_unall_choice:
                for (int i = 0; i < data.size(); i++) {
                    if (EdlitAdapter.getIsSelected().get(i)) {
                        EdlitAdapter.getIsSelected().put(i, false);
                        checkNum--;
                    } else {
                        EdlitAdapter.getIsSelected().put(i, true);
                        checkNum++;
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.btn_del:
                for (int i = 0; i < data.size(); i++) {
                    if (EdlitAdapter.getIsSelected().get(i) == true) {
                        String title = data.get(i).getTitle();
                        dao.del(title);
                    }

                }
                break;
            case R.id.edlit_true:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);


        }
        //监听
        lvEdlit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获得该行上的item
                EdlitAdapter.ViewHolder holder = (EdlitAdapter.ViewHolder) view.getTag();
                //改变checkBox的状态
                holder.cb.toggle();
                //记录CheckBoX的选中状态
                EdlitAdapter.getIsSelected().put(position, holder.cb.isChecked());
                if (holder.cb.isChecked() == true) {
                    checkNum++;
                } else {
                    checkNum--;
                }
            }
        });


    }


}
