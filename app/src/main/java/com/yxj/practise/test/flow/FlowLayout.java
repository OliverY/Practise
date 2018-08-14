package com.yxj.practise.test.flow;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author:  Yxj
 * Time:    2018/8/13 下午7:49
 * -----------------------------------------
 * Description:
 */
public class FlowLayout extends ViewGroup {

    int mWidth;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();

        int measuredWidth = mWidth;
        int measuredHeight = 0;

        int totalHeight = 0;

        int childCount = getChildCount();
        int l = 0;

        for(int i=0;i<childCount;i++){
            View child = getChildAt(i);
//            LayoutParams lp = child.getLayoutParams();

            int childWidth = child.getMeasuredWidth();
            if(l+childWidth >= mWidth){
                l = 0;
            }
            if(l == 0){
                totalHeight += child.getMeasuredHeight();
            }
            l += childWidth;
        }

        if(heightMode == MeasureSpec.EXACTLY){
            measuredHeight = heightSize;
        }else if(heightMode == MeasureSpec.AT_MOST){
            measuredHeight = totalHeight<heightSize?totalHeight:heightSize;
        }
        setMeasuredDimension(measuredWidth,measuredHeight);
    }

    int currentL;
    int currentT;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        currentL = 0;
        currentT = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            // 判断是否换行
            if (currentL+childWidth >= mWidth) {
                currentL = 0;
                currentT += childHeight;
            }

            int childT = currentT;
            int childB = childT + childHeight;

            int childL = currentL;
            int childR = childL + childWidth;

            currentL += childWidth;

            childView.layout(childL, childT, childR, childB);
        }
    }

}
