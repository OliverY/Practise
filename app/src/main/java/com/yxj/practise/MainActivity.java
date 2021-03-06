package com.yxj.practise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yxj.baselib.Constant;
import com.yxj.practise.test.EditFilterActivity;
import com.yxj.practise.test.NestedScrollActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_viewpager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_VIEW_PAGER).navigation();
            }
        });

        findViewById(R.id.btn_nestedscroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(MainActivity.this, NestedScrollActivity.class);
            }
        });

        findViewById(R.id.btn_edit_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(MainActivity.this, EditFilterActivity.class);
            }
        });

        findViewById(R.id.btn_router_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_RA).navigation();
            }
        });

        findViewById(R.id.btn_response_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_RESPONSIBLE_LINK).navigation();
            }
        });

        findViewById(R.id.btn_response_link2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_RESPONSIBLE_LINK2).navigation();
            }
        });

        findViewById(R.id.btn_dash_board).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_DASH_BOARD).navigation();
            }
        });

        findViewById(R.id.btn_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_ADVERTISE).navigation();
            }
        });

        findViewById(R.id.btn_calendar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_CALEANDAR).navigation();
            }
        });

        findViewById(R.id.btn_calendar2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_CALEANDAR_2).navigation();
            }
        });

        findViewById(R.id.btn_rotate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_ROTATE).navigation();
            }
        });

        findViewById(R.id.btn_nested_scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_NESTED_SCROLL).navigation();
            }
        });

        findViewById(R.id.btn_data_binding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_DATA_BINDING).navigation();
            }
        });

        findViewById(R.id.btn_barrage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_BARRAGE).navigation();
            }
        });

        findViewById(R.id.btn_flow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_FLOW).navigation();
            }
        });

        findViewById(R.id.btn_scroll2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(Constant.PATH_NESTED_SCROLL2).navigation();
            }
        });
    }

}
