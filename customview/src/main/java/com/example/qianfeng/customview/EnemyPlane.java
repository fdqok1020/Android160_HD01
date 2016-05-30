package com.example.qianfeng.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2016/5/18.
 */
public class EnemyPlane {
    public float x;
    public   float y;
    private Bitmap map;

    public EnemyPlane(Context context,float x, float y) {
        this.x = x;
        this.y = y;
        map = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy);
    }
    public void move(Canvas canvas, Paint paint){
        y += 10;
        canvas.drawBitmap(map,x,y,paint);
    }
}
