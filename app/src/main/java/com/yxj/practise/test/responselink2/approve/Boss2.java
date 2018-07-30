package com.yxj.practise.test.responselink2.approve;

import android.util.Log;

import com.yxj.practise.test.responselink2.BaseApprove;
import com.yxj.practise.test.responselink2.Request2;

/**
 * Author:  Yxj
 * Time:    2018/7/27 下午2:09
 * -----------------------------------------
 * Description:
 */
public class Boss2 extends BaseApprove {

    @Override
    protected void handle(Request2 request2) {
        Log.e("yxj","Boss 开始处理");
        Log.e("yxj","Boss::只有我来批了");
    }

    @Override
    protected boolean isHandled(Request2 request2) {
        return true;
    }

}
