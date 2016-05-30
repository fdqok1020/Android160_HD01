package com.example.app.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app.constast.Tables;
import com.example.app.constast.bean.Diary;
import com.example.app.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class DiaryDao {
    private SQLiteDatabase db;
    private DBHelper helper;

    public DiaryDao(DBHelper helper) {
        this.helper = helper;
    }

    //添加日志

    public void add(Diary diary) {
        db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", diary.getTitle());
        values.put("content", diary.getContent());
        db.insert(Tables.Diary.TABLE_NAME, null, values);
        db.close();
    }

    //查询所有
    public List<Diary> queryAll() {
        List<Diary> diarys = new ArrayList<>();
        db = helper.getReadableDatabase();
        Cursor cursor = db.query(Tables.Diary.TABLE_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(Tables.Diary.TITLE));
            String content = cursor.getString(cursor.getColumnIndex(Tables.Diary.CONTENT));
            Diary diary = new Diary(title, content);
            diarys.add(diary);
        }
        return diarys;
    }

    //删除数据
    public void del(String title) {
        db = helper.getReadableDatabase();
        String clause = Tables.Diary.TITLE + "=?";
        String[] args = {title};
        db.delete(Tables.Diary.TABLE_NAME, clause, args);
        db.close();

    }

    //修改数据
    public void update(Diary diary){
        db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tables.Diary.TITLE,diary.getTitle());
        values.put(Tables.Diary.CONTENT,diary.getContent());
        String whereClause = Tables.Diary.ID+"=?";
        String[] whereArgs= {String.valueOf(diary.getId())};
        db.update(Tables.Diary.TABLE_NAME,values,whereClause,whereArgs);
        db.close();

    }
}
