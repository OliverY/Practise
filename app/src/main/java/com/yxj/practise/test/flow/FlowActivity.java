package com.yxj.practise.test.flow;

import android.graphics.BitmapRegionDecoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;
import com.yxj.practise.R;
import com.yxj.practise.test.danmu.BarrageView;

import java.util.Random;

/**
 * Author:  Yxj
 * Time:    2018/8/13 上午11:10
 * -----------------------------------------
 * Description:
 */
@Route(path = Constant.PATH_FLOW)
public class FlowActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSend;
    private FlowLayout flow;

    String[] words = {"北京","哈尔滨","666","double kill","first blood"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flow);
        flow = findViewById(R.id.flow);
        btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button tv = new Button(this);
        tv.setText(words[new Random().nextInt(words.length)]);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(lp);
        flow.addView(tv);
    }
}
