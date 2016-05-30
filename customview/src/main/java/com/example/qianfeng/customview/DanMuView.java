package com.example.qianfeng.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016/5/18.
 */
public class DanMuView extends View {
    private Paint mPaint;
    private int count;
    private Handler mHander;
    private Random random= new Random();
    private int randomBaseline;
    private List<BulletItem> bulletItems = new ArrayList<>();
    private int randomX=700;

    public DanMuView(Context context) {
        this(context,null);
    }

    public DanMuView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DanMuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //创建一个画笔
        mPaint =new Paint();
        HandlerThread handlerThread = new HandlerThread("aa");
        handlerThread.start();
        mHander = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                postInvalidate();
            }
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        mPaint.setColor(Color.WHITE);
        if (count%5 == 0) {

            int ran = random.nextInt(500);
            randomBaseline = ran + 60;
            BulletItem bulletItem = new BulletItem(randomX,randomBaseline,ran);
            bulletItems.add(bulletItem);
        }

        for (int i = 0; i < bulletItems.size(); i++) {
            BulletItem bulletItem = bulletItems.get(i);
            bulletItem.move(canvas, mPaint);
            if (bulletItem.x <=-200) {
                bulletItems.remove(i);
            }
        }

        count++;
        mHander.sendEmptyMessageDelayed(1,100);
    }



    class BulletItem {

        public int x;
        public int baseline;
        private int count;

        public BulletItem(int x, int baseline, int count) {
            this.x = x;
            this.baseline = baseline;
            this.count = count;
        }

        public void move(Canvas canvas, Paint paint) {
            x -= 5;
            paint.setTextSize(30);
            canvas.drawText("文字文字" + count,x,baseline,paint);
//            canvas.drawText();
        }
    }
    

}
