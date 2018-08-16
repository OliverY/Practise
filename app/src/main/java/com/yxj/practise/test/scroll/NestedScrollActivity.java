package com.yxj.practise.test.scroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;
import com.yxj.practise.R;

/**
 * Author:  Yxj
 * Time:    2018/8/15 上午10:36
 * -----------------------------------------
 * Description:
 */
@Route(path = Constant.PATH_NESTED_SCROLL2)
public class NestedScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_nestedscroll2);
    }
}
