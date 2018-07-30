package com.yxj.practise.test.responselink2.approve;

import android.util.Log;

import com.yxj.practise.test.responselink2.BaseApprove;
import com.yxj.practise.test.responselink2.Request2;

/**
 * Author:  Yxj
 * Time:    2018/7/27 下午2:15
 * -----------------------------------------
 * Description:
 */
public class Manager2 extends BaseApprove {

    @Override
    protected void handle(Request2 request2) {
        Log.e("yxj", "Manager2::这个我批准了");
    }

    @Override
    protected boolean isHandled(Request2 request2) {
        Log.e("yxj","Manager2 开始处理");
        if(request2.money<100) {
            return true;
        }else{
            Log.e("yxj","Manager2 超过了职权，交给下一个吧");
            return false;
        }
    }
}
