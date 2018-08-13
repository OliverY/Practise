package com.yxj.practise.test.myrxjava;

/**
 * Author:  Yxj
 * Time:    2018/8/2 上午9:00
 * -----------------------------------------
 * Description:
 */
public class MapObservable<T> {

    private OnSubscribe<T> onSubscribe;

    public MapObservable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> MapObservable<T> create(OnSubscribe<T> onSubscribe){
        return new MapObservable<>(onSubscribe);
    }

    public void subscribe(Subscriber<T> subscriber){
        onSubscribe.call(subscriber);
    }

    public <V> MapObservable<V> map(Function1<T,V> function1){
        return function1.fun(this);
    }

    public OnSubscribe<T> getOnSubscribe() {
        return onSubscribe;
    }
}
