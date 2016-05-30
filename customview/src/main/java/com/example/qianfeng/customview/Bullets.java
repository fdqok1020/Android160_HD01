package com.example.qianfeng.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Administrator on 2016/5/18.
 */
public class Bullets {
    public float x;
    public   float y;
    private Bitmap map;

    public Bullets(Context context, float x, float y) {
        this.y = y;
        this.x = x;
        map = BitmapFactory.decodeResource(context.getResources(),R.drawable.bullet);
    }
  public void move(Canvas canvas, Paint paint){
      y -= 10;
      canvas.drawBitmap(map,x,y,paint);
  }

}
