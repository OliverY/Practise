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
public class Manager implements Interceptor {

    int money = 100;

    @Override
    public Response deal(Chain chain) {
        Log.e("yxj","Manager 开始处理");

        Request request = chain.getRequest();

        if(request.money<money){
            return new Response(true,"Manager::我批掉了");
        }
        Log.e("yxj","Manager 超过了职权，交给下一个吧");
        return chain.proceed(request);
    }
}
