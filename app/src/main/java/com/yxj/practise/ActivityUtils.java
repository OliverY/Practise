package com.yxj.practise;

import android.app.Activity;
import android.content.Intent;

import com.yxj.practise.test.NestedScrollActivity;

/**
 * Author:  Yxj
 * Time:    2018/7/19 上午9:03
 * -----------------------------------------
 * Description:
 */
public class ActivityUtils {

    public static void startActivity(Activity activity,Class<? extends Activity> clazz){
        activity.startActivity(new Intent(activity, clazz));
    }
}
