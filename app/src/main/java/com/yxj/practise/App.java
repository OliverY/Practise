package com.yxj.practise;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Author:  Yxj
 * Time:    2018/7/23 下午4:24
 * -----------------------------------------
 * Description:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
    }
}
