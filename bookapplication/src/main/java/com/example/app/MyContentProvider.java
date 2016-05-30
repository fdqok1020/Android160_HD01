package com.example.app;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/5/17.
 */
public class MyContentProvider extends ContentProvider{

    //数据库操作对象
    private MyDatebaseHelper mDbHelper;
    @Override
    public boolean onCreate() {
        mDbHelper = new MyDatebaseHelper(this.getContext(),null);
        return true;

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase writableDatabase = mDbHelper.getWritableDatabase();
        Cursor cursor = writableDatabase.query("tb_book",null,null,null,null,null,null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase writableDatabase = mDbHelper.getWritableDatabase();
        writableDatabase.insert("tb_book",null,values);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase writableDatabase = mDbHelper.getWritableDatabase();
        int i = writableDatabase.delete("tb_book", selection, selectionArgs);
        return i;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase writableDatabase = mDbHelper.getWritableDatabase();
        writableDatabase.update("tb_book",values,selection,selectionArgs);
        return 0;
    }
}
