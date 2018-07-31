package com.yxj.practise.test.calendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.yxj.practise.R;

/**
 * Author:  Yxj
 * Time:    2018/7/31 上午11:36
 * -----------------------------------------
 * Description:
 */
public class CalendarLayout extends FrameLayout {

    public CalendarLayout(@NonNull Context context) {
        this(context,null);
    }

    public CalendarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CalendarLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_calendar,this,true);
    }
}
