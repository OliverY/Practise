package com.yxj.practise.test.rotate3d;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;
import com.yxj.practise.R;

/**
 * Author:  Yxj
 * Time:    2018/7/31 上午9:44
 * -----------------------------------------
 * Description:
 */
@Route(path = Constant.PATH_ROTATE)
public class RotateActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rotate);

        img = findViewById(R.id.img);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                Rotate3DAnim anim = new Rotate3DAnim(Rotate3DAnim.ROTATE_X);
                break;
            case R.id.btn2:
                anim = new Rotate3DAnim(Rotate3DAnim.ROTATE_Y);
                img.startAnimation(anim);
                break;
            case R.id.btn3:
                anim = new Rotate3DAnim(Rotate3DAnim.ROTATE_Z);
                img.startAnimation(anim);
                break;
        }
    }
}
