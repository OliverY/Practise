package com.yxj.practise.test.responselink2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;

/**
 * Author:  Yxj
 * Time:    2018/7/27 下午2:22
 * -----------------------------------------
 * Description:
 */
@Route(path = Constant.PATH_RESPONSIBLE_LINK2)
public class ResponsibleLinkActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Request2 request1 = new Request2(20);
        new ApproveClient().execute(request1);

//        Request2 request2 = new Request2(80);
//        new ApproveClient().execute(request2);
//
//        Request2 request3 = new Request2(120);
//        new ApproveClient().execute(request3);
    }
}
