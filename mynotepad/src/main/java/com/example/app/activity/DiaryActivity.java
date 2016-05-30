package com.example.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.constast.bean.Diary;
import com.example.app.db.dao.DiaryDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class DiaryActivity extends BaseActivity {
    //获取控件
    private EditText diaryEdTitle;
    private EditText diaryEdContent;
    private Diary diary;
    private DiaryDao dao;
    private  int id;
    private List<Diary> data  = new ArrayList<Diary>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity);
        dao = new DiaryDao(helper);
        diaryEdTitle = (EditText) findViewById(R.id.ed_diary_title);
        diaryEdContent = (EditText) findViewById(R.id.ed_diary_content);
        diary = (Diary) getIntent().getSerializableExtra("diary");
        data = dao.queryAll();
       for (int i =0;i<data.size();i++){
           if (diary.getContent().equals(data.get(i).getContent())){
               id=i+1;
           }
       }

        Log.i("Tag", String.valueOf(id));
        diaryEdTitle.setText(diary.getTitle());
        diaryEdContent.setText(diary.getContent());
    }
    public void click(View view){

        String title = diaryEdTitle.getText().toString();
        String content = diaryEdContent.getText().toString();
        Diary diary1 = new Diary(title,content,id);
        switch (view.getId()){
            case R.id.btn_diary_update:
                dao.update(diary1);
                break;
            case R.id.btn_diary_del:
                dao.del(diary.getTitle());
                break;


        }


    }
}
