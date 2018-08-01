package com.yxj.practise.test.nestedscroll;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Author:  Yxj
 * Time:    2018/7/31 下午9:13
 * -----------------------------------------
 * Description:
 */
public class ParentView extends FrameLayout implements NestedScrollingParent {

    private static final String TAG = ParentView.class.getSimpleName();

    NestedScrollingParentHelper helper;

    int mScreenWidth, mScreenHeight;

    public ParentView(@NonNull Context context) {
        this(context, null);
    }

    public ParentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ParentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        helper = new NestedScrollingParentHelper(this);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = getResources().getDisplayMetrics().heightPixels;
    }


    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        helper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
        helper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        final View child = target;
        if (dx > 0) {
            if (child.getRight() + dx > getWidth()) {
                dx = child.getRight() + dx - getWidth();//多出来的
                offsetLeftAndRight(dx);
                consumed[0] += dx;//父亲消耗
            }

        } else {
            if (child.getLeft() + dx < 0) {
                dx = dx + child.getLeft();
                offsetLeftAndRight(dx);
                Log.d(TAG, "dx:" + dx);
                consumed[0] += dx;//父亲消耗
            }

        }

        if (dy > 0) {
            if (child.getBottom() + dy > getHeight()) {
                dy = child.getBottom() + dy - getHeight();
                offsetTopAndBottom(dy);
                consumed[1] += dy;
            }
        } else {
            if (child.getTop() + dy < 0) {
                dy = dy + child.getTop();
                offsetTopAndBottom(dy);
                Log.d(TAG, "dy:" + dy);
                consumed[1] += dy;//父亲消耗
            }
        }

    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return helper.getNestedScrollAxes();
    }

    int downX, downY, lastX, lastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int x = (int) ev.getX();
//        int y = (int) ev.getY();
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = x;
//                downY = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int dx = x - downX;
//                int dy = y
//
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = x - downX;
                int dy = y - downY;

                if (dx >= 0 && dx <= mScreenWidth && dy > 0 && dy < mScreenHeight) {
                    offsetLeftAndRight(dx);
                    offsetTopAndBottom(dy);
                }

                break;
            case MotionEvent.ACTION_UP:

                lastX = x;
                lastY = y;
                break;
        }


        return true;
    }
}
