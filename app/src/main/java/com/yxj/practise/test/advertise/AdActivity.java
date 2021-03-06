package com.yxj.practise.test.advertise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;
import com.yxj.practise.R;
import com.yxj.practise.test.advertise.bean.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  Yxj
 * Time:    2018/7/30 下午3:03
 * -----------------------------------------
 * Description:
 */

@Route(path = Constant.PATH_ADVERTISE)
public class AdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ad);

        BaseAdView<Data> ad = findViewById(R.id.ad);
        List<Data> data = new ArrayList<>();
        data.add(new Data(R.mipmap.user_head_boy,"this is no 1"));
        data.add(new Data(R.mipmap.user_head_girl,"this is no 2"));
        data.add(new Data(R.mipmap.user_head_boy,"this is no 3"));
        data.add(new Data(R.mipmap.user_head_girl,"this is no 4"));

        ad.setData(data);
    }
}
