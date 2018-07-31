package com.yxj.practise.test.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;
import com.yxj.practise.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Author:  Yxj
 * Time:    2018/7/30 下午4:35
 * -----------------------------------------
 * Description:
 */
@Route(path = Constant.PATH_CALEANDAR_2)
public class Calendar2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);

        CalendarMonth calendarView = findViewById(R.id.calendar);
        List<DateBean> dateList = new ArrayList<>();
        for(int i=1;i<=31;i++){
            Calendar calendar = Calendar.getInstance();
            calendar.set(2018,6,i);
            dateList.add(new DateBean(calendar));
        }
        calendarView.setData(dateList);
    }
}
