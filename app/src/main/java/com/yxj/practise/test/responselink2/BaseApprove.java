package com.yxj.practise.test.responselink2;

/**
 * Author:  Yxj
 * Time:    2018/7/27 下午2:45
 * -----------------------------------------
 * Description:
 */
public abstract class BaseApprove implements IApprove {

    protected IApprove nextApprove;

    @Override
    public void approve(Request2 request2) {
        if(isHandled(request2)){
            handle(request2);
        }else{
            nextApprove.approve(request2);
        }
    }

    protected abstract void handle(Request2 request2);

    protected abstract boolean isHandled(Request2 request2);

    @Override
    public IApprove buildNext(IApprove approve) {
        nextApprove = approve;
        return nextApprove;
    }
}
