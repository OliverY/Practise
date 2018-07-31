package com.yxj.practise.test.calendar;

import java.util.Calendar;

/**
 * Author:  Yxj
 * Time:    2018/7/30 下午6:06
 * -----------------------------------------
 * Description:
 */
public class Test {

    public static void main(String[] args){
        fun();
    }

    private static void fun(){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2018,6,7);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.print("day of week::"+dayOfWeek);
        System.out.print("date::"+calendar.getTime().toString());
    }
}
