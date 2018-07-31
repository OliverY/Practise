package com.yxj.practise.test.advertise.anim;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.yxj.practise.test.advertise.BaseAdView;


/**
 * Author:  Yxj
 * Time:    2018/7/31 上午8:21
 * -----------------------------------------
 * Description:
 */
public class AnimPage {

    public static Animation generateAnimation(int inOrOut){
        AnimationSet set = new AnimationSet(true);

        TranslateAnimation animation = null;
        RotateAnimation rotateAnimation = null;
        if(inOrOut == BaseAdView.IN){
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,
                    Animation.RELATIVE_TO_SELF,1,Animation.RELATIVE_TO_SELF,0);
            rotateAnimation = new RotateAnimation(0,90,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        }else if(inOrOut == BaseAdView.OUT){
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,
                    Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-1);

            rotateAnimation = new RotateAnimation(0,90,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        }
        set.addAnimation(animation);
        set.addAnimation(rotateAnimation);

        return set;
    }
}
