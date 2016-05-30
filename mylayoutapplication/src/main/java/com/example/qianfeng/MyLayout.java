package com.example.qianfeng;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/5/19.
 */
public class MyLayout extends ViewGroup {
    public MyLayout(Context context) {
        this(context,null);
    }

    public MyLayout(Context context, AttributeSet attrs) {
       this(context, attrs,0);
    }

    public MyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int childCount = getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View childView = getChildAt(i);
            childView.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int totalHeight = 0;
        for (int i = 0; i <2 ; i++) {
            View childView = getChildAt(i);
            int measuredWidth = childView.getMeasuredWidth();
            int measuredHeight = childView.getMeasuredHeight();
            Log.i("+++++++","======="+measuredHeight);
            int left = 10;//距离左边的宽度
            int right = 10+measuredWidth;//右边到屏幕左边的距离；
//            int top =10+i*measuredHeight;// 10 30
//            int bottom = 10;
//            int bottom = 10+measuredHeight;
            int top =10+ measuredHeight * i;
            Log.i("+++++++","++++++++top="+top);
            Log.i("+++++++","++++++++bottom="+(top+measuredHeight));
            childView.layout(left,top,right,top+measuredHeight);
        }






    }
}
