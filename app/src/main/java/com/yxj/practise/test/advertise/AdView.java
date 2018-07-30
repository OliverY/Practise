package com.yxj.practise.test.advertise;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Author:  Yxj
 * Time:    2018/7/30 下午2:42
 * -----------------------------------------
 * Description:
 */
public class AdView extends FrameLayout {

    private TextView viewIn;
    private TextView viewOut;

    private static final int IN = 1;
    private static final int OUT = 2;

    private List<String> data;
    private int current;

    public AdView(@NonNull Context context) {
        super(context);
    }

    public AdView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        viewIn = (TextView) generateChildView("this is in");
        viewOut = (TextView) generateChildView("this is out");

        addView(viewIn);
        addView(viewOut);
    }

    private void start(){
        Animation animationIn = generateAnimation(IN);
        animationIn.setAnimationListener(new Animation.AnimationListener() {
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

        viewOut.setText(data.get(current%data.size()));
        viewIn.setText(data.get((current+1)%data.size()));

        current++;
        viewIn.startAnimation(animationIn);
        viewOut.startAnimation(generateAnimation(OUT));
    }

    private Animation generateAnimation(int inOrOut){
        TranslateAnimation animation = null;
        if(inOrOut == IN){
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,
                    Animation.RELATIVE_TO_SELF,1,Animation.RELATIVE_TO_SELF,0);

        }else if(inOrOut == OUT){
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,
                    Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-1);
        }
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setStartOffset(1000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        return animation;
    }

    private View generateChildView(String text){
        TextView textView = new TextView(getContext());
        textView.setText(text);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    private int dp2px(int dp){
        float density = getResources().getDisplayMetrics().density;
        return (int) (density*dp+0.5);
    }


    public void setData(List<String> data){
        this.data = data;
        start();
    }

}
