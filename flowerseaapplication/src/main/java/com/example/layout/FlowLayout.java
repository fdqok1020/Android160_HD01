package com.example.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/5/24.
 */
public class FlowLayout extends ViewGroup implements View.OnClickListener{
    public FlowLayout(Context context) {
        this(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量每一个子控件的大小
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //测量子控件大小
            childView.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
       //计算有多少个控件需要显示

        int childCount = getChildCount();
        int totalWidth = 0;
        int higherHeight = 0;

        int row = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.setOnClickListener(this);
            childView.setTag(i);
            int measuredWidth = childView.getMeasuredWidth();
            int measuredHeight = childView.getMeasuredHeight();
            higherHeight = higherHeight < measuredHeight?measuredHeight:higherHeight;
            int left = 10;
            int right = 10 + measuredWidth;

            totalWidth += right;

            if (totalWidth >= r) {
                row++;
                totalWidth = measuredWidth + 10;
            }
            int top = 10 + row*(higherHeight+10);
            int bottom = top + measuredHeight;

            childView.layout(totalWidth-measuredWidth,top,totalWidth,bottom);
        }



    }

    @Override
    public void onClick(View v) {

    }
}
