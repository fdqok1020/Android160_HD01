package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.contanst.DBConstast;
import com.example.contanst.Tables;

/**
 * Created by Administrator on 2016/4/30.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String TAG ="DBHelper" ;

    public DBHelper(Context context) {
        super(context, DBConstast.DATABASE_NAME, null,DBConstast.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //creat table diary（dName text,dConnten text）;
        String sql="CREATE TABLE "+ Tables.Diary.TABLE_NAME+"("+Tables.Diary.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Tables.Diary.CONTENT+" TEXT"+")";
        db.execSQL(sql);
//        Log.i(TAG,"我执行了");
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
