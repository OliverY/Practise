package com.yxj.practise.test.myrxjava;

/**
 * Author:  Yxj
 * Time:    2018/8/2 上午9:00
 * -----------------------------------------
 * Description:
 */
public class Observable<T> {

    private OnSubscribe<T> onSubscribe;

    public Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe){
        return new Observable<>(onSubscribe);
    }

    public void subscribe(Subscriber<T> subscriber){
        onSubscribe.call(subscriber);
    }
}
