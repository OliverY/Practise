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
                img.startAnimation(anim);
//                rotateOnXCoordinate();
                break;
            case R.id.btn2:
//                rotateOnYCoordinate();
                anim = new Rotate3DAnim(Rotate3DAnim.ROTATE_Y);
                img.startAnimation(anim);
                break;
            case R.id.btn3:
//                rotateAnimHorizon();
                anim = new Rotate3DAnim(Rotate3DAnim.ROTATE_Z);
                img.startAnimation(anim);
                break;
        }
    }

    // 以X轴为轴心旋转
    private void rotateOnXCoordinate() {
        float centerX = img.getWidth() / 2.0f;
        float centerY = img.getHeight() / 2.0f;
        float depthZ = 0f;
        Rotate3dAnimation rotate3dAnimationX = new Rotate3dAnimation(0, 180, centerX, centerY, depthZ, Rotate3dAnimation.ROTATE_X_AXIS, true);
        rotate3dAnimationX.setDuration(1000);
        img.startAnimation(rotate3dAnimationX);
    }

    // 以X轴为轴心旋转
    private void rotateOnYCoordinate() {
        float centerX = img.getWidth() / 2.0f;
        float centerY = img.getHeight() / 2.0f;
        float centerZ = 0f;

        Rotate3dAnimation rotate3dAnimationX = new Rotate3dAnimation(0, 180, centerX, centerY, centerZ, Rotate3dAnimation.ROTATE_Y_AXIS, true);
        rotate3dAnimationX.setDuration(1000);
        img.startAnimation(rotate3dAnimationX);
    }

    // 以Z轴为轴心旋转---等价于普通平面旋转动画
    private void rotateAnimHorizon() {
        float centerX = img.getWidth() / 2.0f;
        float centerY = img.getHeight() / 2.0f;
        float centerZ = 0f;

        Rotate3dAnimation rotate3dAnimationX = new Rotate3dAnimation(180, 0, centerX, centerY, centerZ, Rotate3dAnimation.ROTATE_Z_AXIS, true);
        rotate3dAnimationX.setDuration(1000);
        img.startAnimation(rotate3dAnimationX);

        // 下面是使用android自带的旋转动画
        // RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // rotateAnimation.setDuration(1000);
        // img.startAnimation(rotateAnimation);
    }
}
