package com.example.qianfeng.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/5/18.
 */
public class PlanView extends View {
    private float x;
    private float y;
    private Paint paint;
    private Context context;
    private int count;
    private Bitmap map;
    private List<Bullets> bulletArray = new ArrayList<>();
    private List<EnemyPlane> planeArray = new ArrayList<>();

    public PlanView(Context context) {
        this(context, null);
    }

    public PlanView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    private void init(Context context) {
         paint = new Paint();
        this.context = context;
        map = BitmapFactory.decodeResource(context.getResources(),R.drawable.myplane);
    }

    @Override
    //添加事件
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                //获取鼠标的位置
                 x = event.getX();
                 y = event.getY();
                break;
        }
        return  true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //用画笔绘制一张图片
        super.onDraw(canvas);
        canvas.drawBitmap(map,x -33,y-40,paint);

        if (count % 5 == 0) {
            Bullets bullet = new Bullets(context, x - 33, y);
            bulletArray.add(bullet);
        }

        for (int i = 0; i < bulletArray.size(); i++) {
            Bullets bullets = bulletArray.get(i);
            bullets.move(canvas,paint);
            if (bullets.y< 0) {
                bulletArray.remove(i);
            }
        }
        if (count%20 == 0){
            Random random = new Random();
            float i = random.nextInt(760);
            float m = random.nextInt(670);
            EnemyPlane plane = new EnemyPlane(context,i,m);
            planeArray.add(plane);
        }
        for (int i = 0; i <planeArray.size() ; i++) {
            EnemyPlane planes = planeArray.get(i);
            planes.move(canvas,paint);

        }
    count++;
        //实时刷新画布
        invalidate();
    }
}
