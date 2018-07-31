package com.yxj.practise.test.rotate3d;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Author:  Yxj
 * Time:    2018/7/31 上午9:37
 * -----------------------------------------
 * Description:
 */
public class Rotate3DAnim extends Animation {

    int centerX,centerY;
    Camera camera;

    public static final int ROTATE_X = 1;
    public static final int ROTATE_Y = 2;
    public static final int ROTATE_Z = 3;
    int rotateDirection;

    public Rotate3DAnim(int rotateDirection) {
        super();
        this.rotateDirection = rotateDirection;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);

        centerX = width/2;
        centerY = height/2;

        camera = new Camera();

        setDuration(2000);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        Matrix matrix = t.getMatrix();
        camera.save();
        if(rotateDirection == ROTATE_X){
            camera.rotateX(360*interpolatedTime);
        }else if(rotateDirection == ROTATE_Y){
            camera.rotateY(360*interpolatedTime);
        }else if(rotateDirection == ROTATE_Z){
            camera.rotateZ(360*interpolatedTime);
        }

        camera.getMatrix(matrix);
//        //设置翻转中心点
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);

        camera.restore();
    }
}
