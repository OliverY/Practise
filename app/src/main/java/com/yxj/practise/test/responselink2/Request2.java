package com.yxj.practise.test.responselink2;

import android.util.Log;

/**
 * Author:  Yxj
 * Time:    2018/7/27 下午2:11
 * -----------------------------------------
 * Description:
 */
public class Request2 {

    public int money;

    public Request2(int money) {
        Log.e("yxj","申请报销：："+money+" 元");
        this.money = money;
    }
}
