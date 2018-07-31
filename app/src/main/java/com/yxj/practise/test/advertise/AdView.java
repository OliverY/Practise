package com.yxj.practise.test.advertise;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxj.practise.R;
import com.yxj.practise.test.advertise.anim.AnimPage;
import com.yxj.practise.test.advertise.anim.AnimTranslate;
import com.yxj.practise.test.advertise.bean.Data;

/**
 * Author:  Yxj
 * Time:    2018/7/30 下午4:13
 * -----------------------------------------
 * Description:
 */
public class AdView extends BaseAdView<Data> {

    public AdView(@NonNull Context context) {
        super(context);
    }

    public AdView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View generateChildView() {
        View childView = View.inflate(getContext(), R.layout.item_ad,null);
        return childView;
    }

    @Override
    protected void setData(View view, Data data) {
        ImageView img = view.findViewById(R.id.img);
        img.setImageResource(data.resourceId);
        TextView tv = view.findViewById(R.id.tv);
        tv.setText(data.tips);
    }

    @Override
    public Animation getAdAnimation(int inOrOut) {
        return AnimTranslate.generateAnimation(inOrOut);
    }
}
