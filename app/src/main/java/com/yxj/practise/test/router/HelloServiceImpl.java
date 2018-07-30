package com.yxj.practise.test.router;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;

/**
 * Author:  Yxj
 * Time:    2018/7/25 上午11:11
 * -----------------------------------------
 * Description:
 */

@Route(path = Constant.SERVICE_HELLO)
public class HelloServiceImpl implements HelloService {

    Context context;

    @Override
    public void sayHi(String name) {
        Toast.makeText(context,"hello "+name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
