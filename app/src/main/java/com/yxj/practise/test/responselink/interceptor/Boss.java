package com.yxj.practise.test.responselink.interceptor;

import android.util.Log;

import com.yxj.practise.test.responselink.Interceptor;
import com.yxj.practise.test.responselink.Request;
import com.yxj.practise.test.responselink.Response;

/**
 * Author:  Yxj
 * Time:    2018/7/26 上午8:22
 * -----------------------------------------
 * Description:
 */
public class Boss implements Interceptor {

    @Override
    public Response deal(Chain chain) {
        Log.e("yxj","Boss 开始处理");
        return new Response(true,"Boss::只有我来批了");
    }
}
