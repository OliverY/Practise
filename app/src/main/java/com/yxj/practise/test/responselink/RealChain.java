package com.yxj.practise.test.responselink;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author:  Yxj
 * Time:    2018/7/26 上午8:31
 * -----------------------------------------
 * Description:
 */
public class RealChain implements Interceptor.Chain {

    private Request request;
    private List<Interceptor> interceptors;
    private int index;

    public RealChain(Request request,List<Interceptor> interceptors,int index) {
        this.request = request;
        this.interceptors = interceptors;
        this.index = index;
    }

    @Override
    public Request getRequest() {
        return request;
    }

    @Override
    public Response proceed(Request request) {
        Response response = null;
        if(interceptors.size()>index){
            RealChain realChain = new RealChain(request,interceptors,index+1);
            response = interceptors.get(index).deal(realChain);
        }
        return response;
    }

    public void addInterceptors(Interceptor interceptor) {
        interceptors.add(interceptor);
    }
}
