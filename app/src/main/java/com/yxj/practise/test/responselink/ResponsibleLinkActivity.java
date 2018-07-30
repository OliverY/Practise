package com.yxj.practise.test.responselink;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;

/**
 * Author:  Yxj
 * Time:    2018/7/26 上午8:29
 * -----------------------------------------
 * Description:
 */
@Route(path = Constant.PATH_RESPONSIBLE_LINK)
public class ResponsibleLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RealChainClient realChainClient = new RealChainClient();
        Request requestA = new Request(30);
        Log.e("yxj","员工A: 我想申请30块报销");
        Log.e("yxj","领导回复：："+realChainClient.execute(requestA).result);

        Request requestB = new Request(120);
        Log.e("yxj","员工B：我想申请120块报销");
        Log.e("yxj","领导回复：："+realChainClient.execute(requestB).result);
    }
}