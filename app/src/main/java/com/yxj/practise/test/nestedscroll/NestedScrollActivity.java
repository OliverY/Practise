package com.yxj.practise.test.nestedscroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;
import com.yxj.practise.R;

/**
 * Author:  Yxj
 * Time:    2018/7/31 下午9:18
 * -----------------------------------------
 * Description:
 */

@Route(path = Constant.PATH_NESTED_SCROLL)
public class NestedScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nested_scroll);
    }
}
