package com.yxj.practise.test.responselink2;

import com.yxj.practise.test.responselink2.approve.Boss2;
import com.yxj.practise.test.responselink2.approve.DepartMentLeader2;
import com.yxj.practise.test.responselink2.approve.GroupLeader2;
import com.yxj.practise.test.responselink2.approve.Manager2;

/**
 * Author:  Yxj
 * Time:    2018/7/27 下午2:38
 * -----------------------------------------
 * Description:
 */
public class ApproveClient {

    private final IApprove approve;

    public ApproveClient(){
        approve = new GroupLeader2();
        approve.buildNext(new DepartMentLeader2())
                .buildNext(new Manager2())
                .buildNext(new Boss2());
    }



    public void execute(Request2 request2){
        approve.approve(request2);
    }

}
