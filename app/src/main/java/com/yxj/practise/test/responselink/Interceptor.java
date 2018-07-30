package com.yxj.practise.test.responselink;

/**
 * Author:  Yxj
 * Time:    2018/7/26 上午8:19
 * -----------------------------------------
 * Description:
 */
public interface Interceptor {

    Response deal(Chain chain);

    interface Chain{

        Request getRequest();

        Response proceed(Request request);

    }
}
