package com.yxj.practise.test.advertise.anim;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.yxj.practise.test.advertise.BaseAdView;


/**
 * Author:  Yxj
 * Time:    2018/7/31 上午8:21
 * -----------------------------------------
 * Description:
 */
public class AnimTranslate {

    public static Animation generateAnimation(int inOrOut){
        TranslateAnimation animation = null;
        if(inOrOut == BaseAdView.IN){
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,
                    Animation.RELATIVE_TO_SELF,1,Animation.RELATIVE_TO_SELF,0);

        }else if(inOrOut == BaseAdView.OUT){
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,
                    Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-1);
        }
        return animation;
    }
}
