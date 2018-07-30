package com.yxj.practise.test.responselink2;

/**
 * Author:  Yxj
 * Time:    2018/7/27 下午2:10
 * -----------------------------------------
 * Description:
 */
public interface IApprove {

    void approve(Request2 request2);

    IApprove buildNext(IApprove approve);
}
