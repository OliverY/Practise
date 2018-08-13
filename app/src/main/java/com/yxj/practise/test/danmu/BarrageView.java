package com.yxj.practise.test.danmu;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author:  Yxj
 * Time:    2018/8/13 上午11:07
 * -----------------------------------------
 * Description:
 */
public class BarrageView extends RelativeLayout {

    int mScreenWidth;
    int mViewHeight;
    int row;
    int rowHeight;
    List<TextView> childViews;

    public BarrageView(Context context) {
        this(context,null);
    }

    public BarrageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BarrageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        childViews = new ArrayList<>();
        row = 10;
    }

    public void addItemView(String msg){
        final TextView textView = new TextView(getContext());
        textView.setText(msg);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp.topMargin = new Random().nextInt(row)*rowHeight;
        Log.e("yxj","marginTop::"+lp.topMargin);
        textView.setLayoutParams(lp);
        addView(textView);

        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击",Toast.LENGTH_SHORT).show();
            }
        });

        int w = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        textView.measure(w,h);

        ViewPropertyAnimator animator = textView.animate().translationXBy(mScreenWidth+textView.getMeasuredWidth());
        long duration = new Random().nextInt(2000)+3000;
        animator.setDuration(duration);
        animator.setInterpolator(new LinearInterpolator());
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(textView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();

        childViews.add(textView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mViewHeight = getMeasuredHeight();
        rowHeight = mViewHeight/row;

        for(int i=0;i<childViews.size();i++){
            TextView item = childViews.get(i);
//            l = l-item.getMeasuredWidth();
//            item.layout(l,t,r,b);
            LayoutParams lp = (LayoutParams) item.getLayoutParams();
            item.layout(-item.getMeasuredWidth(),lp.topMargin,0,lp.topMargin+item.getMeasuredHeight());
        }

//        int childCount = getChildCount();
//        for(int i=0;i<childCount;i++){
//            View view = getChildAt(i);
//            if(view!=null){
//                LayoutParams lp = (LayoutParams) view.getLayoutParams();
//                view.layout(-view.getMeasuredWidth(),lp.topMargin,0,lp.topMargin+view.getMeasuredHeight());
//            }
//        }

    }
}
