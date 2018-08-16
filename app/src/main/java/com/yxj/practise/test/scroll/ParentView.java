package com.yxj.practise.test.scroll;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Author:  Yxj
 * Time:    2018/8/15 上午8:52
 * -----------------------------------------
 * Description:
 */
public class ParentView extends FrameLayout implements NestedScrollingParent {

    private static final String TAG = ParentView.class.getSimpleName();
    private NestedScrollingParentHelper parentHelper;
    private int width;
    private int height;

    public ParentView(Context context) {
        this(context, null);
    }

    public ParentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        parentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        Log.e(TAG, "parent view onStartNestedScroll true");
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        parentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
        parentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e(TAG, "parent view onNestedScroll");
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @Nullable int[] consumed) {
        if(dx>0){
            if (getRight() <= width) {
                offsetLeftAndRight(dx);
                consumed[0] += dx;
            }
        }else {
            if (getLeft() >= 0) {
                offsetLeftAndRight(dx);
                consumed[0] += dx;
            }
        }

        if(dy>0){
            if(getBottom()<=height){
                offsetTopAndBottom(dy);
                consumed[1] +=dy;
            }
        }else{
            if(getTop()>=0){
                offsetTopAndBottom(dy);
                consumed[1] +=dy;
            }
        }

        // 当在超过边框的时候，就由parent view来消耗
//        if (dx > 0) {// 向右移
//            if (child.getRight() + dx > getWidth()) {
//                dx = child.getRight() + dx - getWidth();
//                offsetLeftAndRight(dx);
//                consumed[0] += dx;
//
//                Log.e(TAG,"parent view 消耗");
//            }
//        } else {
//            if (child.getLeft() + dx < 0) {
//                dx = child.getLeft() + dx;
//                offsetLeftAndRight(dx);
//                consumed[0] += dx;
//
//                Log.e(TAG,"parent view 消耗");
//            }
//        }
//
//        if (dy > 0) {// 向下滑动
//            if (child.getBottom() + dy > getHeight()) {
//                dy = child.getBottom() + dy - getHeight();
//                offsetTopAndBottom(dy);
//                consumed[1] += dy;
//
//                Log.e(TAG,"parent view 消耗");
//            }
//        } else {
//            if (child.getTop() + dy < 0) {
//                dy = child.getTop() + dy;
//                offsetTopAndBottom(dy);
//                consumed[1] += dy;
//
//                Log.e(TAG,"parent view 消耗");
//            }
//        }

        Log.e(TAG, "parent view 进入");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getResources().getDisplayMetrics().widthPixels;
        height = getResources().getDisplayMetrics().heightPixels;

    }

    int downX = 0;
    int downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = x - downX;
                int dy = y - downY;

                offsetLeftAndRight(dx);
                offsetTopAndBottom(dy);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }
}
