package com.yxj.practise.test.router;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Author:  Yxj
 * Time:    2018/7/25 上午10:59
 * -----------------------------------------
 * Description:
 */
public interface HelloService extends IProvider{

    void sayHi(String name);
}
