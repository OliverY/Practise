package com.yxj.practise.test.danmu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;
import com.yxj.practise.R;

import java.util.Random;

/**
 * Author:  Yxj
 * Time:    2018/8/13 上午11:10
 * -----------------------------------------
 * Description:
 */
@Route(path = Constant.PATH_BARRAGE)
public class BarrageActivity extends AppCompatActivity implements View.OnClickListener {

    private BarrageView barrage;
    private Button btnSend;

    String[] words = {"北京","哈尔滨","666","double kill","first blood"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_barrage);
        barrage = findViewById(R.id.barrage);
        btnSend = findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        barrage.addItemView(words[new Random().nextInt(words.length)]);
    }
}
