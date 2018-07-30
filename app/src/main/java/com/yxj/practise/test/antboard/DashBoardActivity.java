package com.yxj.practise.test.antboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;
import com.yxj.practise.R;

/**
 * Author:  Yxj
 * Time:    2018/7/28 上午8:51
 * -----------------------------------------
 * Description:
 */

@Route(path = Constant.PATH_DASH_BOARD)
public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dash_board);


    }
}
