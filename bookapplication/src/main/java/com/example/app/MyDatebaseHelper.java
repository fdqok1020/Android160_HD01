package com.example.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/5/17.
 */
public class MyDatebaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "book";
    public static final int VERSION_CODE = 1;
    //创建图书表
    public static final String CREATE_TB_BOOK = "create table tb_book (" +
            "_id integer primary key autoincrement," +
            "book_name string," +
            "book_price float," +
            "book_author string" +
            ");";
    public MyDatebaseHelper (Context context,SQLiteDatabase.CursorFactory factory) {
        this(context,DB_NAME,factory,VERSION_CODE);
    }
    public MyDatebaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TB_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
