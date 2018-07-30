package com.yxj.practise.test.antboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.math.MathUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;
import com.yxj.practise.R;

import java.util.Random;

/**
 * Author:  Yxj
 * Time:    2018/7/28 上午8:51
 * -----------------------------------------
 * Description:
 */

@Route(path = Constant.PATH_DASH_BOARD)
public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etScore;
    private DashBoardView dashBoard;

    int score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dash_board);

        etScore = findViewById(R.id.et_score);
        dashBoard = findViewById(R.id.dash_board);
        Button btnSetScore = findViewById(R.id.btn_set_score);
        Button btnRandomScore = findViewById(R.id.btn_random_score);

        btnSetScore.setOnClickListener(this);
        btnRandomScore.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_set_score:
                score = Integer.parseInt(etScore.getText().toString());
                dashBoard.setScore(score);
                break;
            case R.id.btn_random_score:
                score = new Random().nextInt(600)+350;
                dashBoard.setScore(score);
                break;
        }
    }
}
