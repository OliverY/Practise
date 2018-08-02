package com.yxj.practise.test.myrxjava;

/**
 * Author:  Yxj
 * Time:    2018/8/2 上午9:02
 * -----------------------------------------
 * Description:
 */
public class Test {

    public static void main(String[] args){

        Observable.create(new OnSubscribe<String>() {
            @Override
            public void call(Subscriber<String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("world");
                subscriber.onComplete();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });

    }


}
