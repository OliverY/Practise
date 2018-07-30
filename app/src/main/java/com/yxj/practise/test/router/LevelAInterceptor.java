package com.yxj.practise.test.router;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * Author:  Yxj
 * Time:    2018/7/25 上午9:30
 * -----------------------------------------
 * Description:
 */
@Interceptor(priority = 7,name = "abc")
public class LevelAInterceptor implements IInterceptor {

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.e("yxj","aabbcc :: postcard::"+postcard+"  callback::"+callback);

        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {
        Log.e("yxj","init");
    }
}