package com.example.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.constast.bean.Diary;
import com.example.app.db.dao.DiaryDao;

/**
 * Created by Administrator on 2016/4/30.
 */
public class AddActivity extends BaseActivity {

        //控件
   private EditText editTltle;
    private EditText editContent;
    private DiaryDao dao;

    //数据
    private Diary diary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        dao = new DiaryDao(helper);
        editTltle = (EditText) findViewById(R.id.ed_title);
        editContent = (EditText) findViewById(R.id.ed_content);

    }
    public  void click(View view){
        switch (view.getId()){
            case R.id.btn_submit:
             String title = editTltle.getText().toString();
                String content = editContent.getText().toString();
                diary = new Diary(title,content);
                dao.add(diary);
                Toast.makeText(getApplicationContext(), diary.toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        }

    }

}
