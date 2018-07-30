package com.yxj.practise.test.responselink;

import com.yxj.practise.test.responselink.interceptor.Boss;
import com.yxj.practise.test.responselink.interceptor.DepartMentLeader;
import com.yxj.practise.test.responselink.interceptor.GroupLeader;
import com.yxj.practise.test.responselink.interceptor.Manager;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  Yxj
 * Time:    2018/7/26 上午8:51
 * -----------------------------------------
 * Description:
 */
public class RealChainClient {

    RealChain realChain;
    List<Interceptor> interceptors;

    public RealChainClient() {
         interceptors = new ArrayList<>();
    }

    public void addInterceptor(Interceptor interceptor){
        interceptors.add(interceptor);
    }

    public Response execute(Request request){
        interceptors.add(new GroupLeader());
        interceptors.add(new DepartMentLeader());
        interceptors.add(new Manager());
        interceptors.add(new Boss());
        realChain = new RealChain(request,interceptors,0);
        return realChain.proceed(request);
    }
}
