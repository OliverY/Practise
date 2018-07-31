package com.yxj.practise.test.advertise;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Author:  Yxj
 * Time:    2018/7/30 下午2:42
 * -----------------------------------------
 * Description:
 */
public abstract class BaseAdView<T> extends FrameLayout {

    private View viewIn;
    private View viewOut;

    private Animation ANI_IN;
    private Animation ANI_OUT;

    public static final int IN = 1;
    public static final int OUT = 2;

    private List<T> data;
    private int current;
    private long duration = 2000;

    public BaseAdView(@NonNull Context context) {
        super(context);
    }

    public BaseAdView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseAdView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initView();
        initAnimation();

        addView(viewIn);
        addView(viewOut);
    }

    private void initAnimation() {
        ANI_IN = generateAnimation(IN);
        ANI_OUT = generateAnimation(OUT);
    }

    protected abstract View generateChildView();

    private void initView(){
        viewIn = generateChildView();
        viewOut = generateChildView();
    }

    protected abstract void setData(View view,T t);

    private void start(){
        ANI_IN.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        setData(viewOut,data.get(current%data.size()));
        setData(viewIn,data.get((current+1)%data.size()));

        current++;
        viewIn.startAnimation(ANI_IN);
        viewOut.startAnimation(ANI_OUT);
    }

    private Animation generateAnimation(int inOrOut){
        Animation animation = getAdAnimation(inOrOut);
        animation.setDuration(duration/2);
        animation.setFillAfter(true);
        animation.setStartOffset(duration/2);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        return animation;
    }

    public abstract Animation getAdAnimation(int inOrOut);

    private int dp2px(int dp){
        float density = getResources().getDisplayMetrics().density;
        return (int) (density*dp+0.5);
    }

    public void setData(List<T> data){
        this.data = data;
        start();
    }

    public void setDuration(long duration){
        this.duration = duration;
    }

}
