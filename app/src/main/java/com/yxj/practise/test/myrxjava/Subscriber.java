package com.yxj.practise.test.myrxjava;

/**
 * Author:  Yxj
 * Time:    2018/8/2 上午8:59
 * -----------------------------------------
 * Description:
 */
public interface Subscriber<T> {

    void onNext(T t);

    void onComplete();
}
