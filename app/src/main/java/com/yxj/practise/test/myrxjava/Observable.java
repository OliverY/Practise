//package com.yxj.practise.test.myrxjava;
//
//import android.view.LayoutInflater;
//import android.view.ViewStub;
//
//import java.util.zip.Inflater;
//
///**
// * Author:  Yxj
// * Time:    2018/8/2 上午9:00
// * -----------------------------------------
// * Description:
// */
//public class Observable<T> {
//
//    private OnSubscribe<T> onSubscribe;
//
//    public Observable(OnSubscribe<T> onSubscribe) {
//        this.onSubscribe = onSubscribe;
//    }
//
//    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe){
//        return new Observable<>(onSubscribe);
//    }
//
//    public void subscribe(Subscriber<T> subscriber){
//        onSubscribe.call(subscriber);
//    }
//
//    public <V> Observable<V> map(Function1<T,V> function1){
//        return function1.fun(this);
//    }
//
//    public OnSubscribe<T> getOnSubscribe() {
//        return onSubscribe;
//    }
//}
