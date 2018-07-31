package com.yxj.practise.test.calendar;

import java.util.Calendar;

/**
 * Author:  Yxj
 * Time:    2018/7/30 下午4:53
 * -----------------------------------------
 * Description:
 */
public class DateBean {

    public static final int STYLE_PASSED = -1;
    public static final int STYLE_TODAY = 0;
    public static final int STYLE_FUTURE = 1;

    int x;// 第几列
    int y;// 第几行
    boolean isSelected; //是否选中
    // -1:passed  0:today  1:future
    int style;   // 是否已经过去

    Calendar date;

    public DateBean(Calendar date) {
        this.date = date;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
