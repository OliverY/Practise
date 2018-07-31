package com.yxj.practise.test.calendar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Author:  Yxj
 * Time:    2018/7/31 上午11:45
 * -----------------------------------------
 * Description:
 */
public class CalendarHeader extends FrameLayout {

    private TextView title;

    public CalendarHeader(@NonNull Context context) {
        this(context,null);
    }

    public CalendarHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CalendarHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        title = new TextView(context);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        title.setLayoutParams(params);
        title.setGravity(Gravity.CENTER);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        addView(title);


    }

    public void setTitle(String text){
        title.setText(text);
    }

}
