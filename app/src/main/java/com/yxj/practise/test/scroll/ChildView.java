package com.yxj.practise.test.scroll;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

/**
 * Author:  Yxj
 * Time:    2018/8/15 上午8:53
 * -----------------------------------------
 * Description:
 */
public class ChildView extends View implements NestedScrollingChild {

    private static final String TAG = ChildView.class.getSimpleName();
    private NestedScrollingChildHelper childHelper;

    public ChildView(Context context) {
        this(context,null);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChildView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        childHelper = new NestedScrollingChildHelper(this);
        childHelper.setNestedScrollingEnabled(true);
    }

    int mDownX = 0;
    int mDownY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX = x;
                mDownY = y;

                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL|ViewCompat.SCROLL_AXIS_HORIZONTAL);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = x - mDownX;
                int dy = y - mDownY;

//                Log.e(TAG,"之前 dx="+dx);
                int[] consumed = new int[2];
                int[] offsetInWindow = new int[2];

                if(dispatchNestedPreScroll(dx,dy,consumed,offsetInWindow)){
                    dx -= consumed[0];
                    dy -= consumed[1];
                }

                offsetLeftAndRight(dx);
                offsetTopAndBottom(dy);

                break;
            case MotionEvent.ACTION_UP:
                stopNestedScroll();
                break;
        }

        return true;
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return childHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        childHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return childHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return childHelper.dispatchNestedScroll(dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed,offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return childHelper.dispatchNestedPreScroll(dx,dy,consumed,offsetInWindow);
    }
}
