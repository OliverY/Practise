package com.yxj.practise.test.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Author:  Yxj
 * Time:    2018/7/30 下午4:34
 * -----------------------------------------
 * Description:
 */
public class CalendarView extends View {

    Paint wordPaint;
    Paint itemBgPaint;
    Paint testPaint;


    List<DateBean> data;

    int DEFAULT_RADIUS = 20;

    int startIndex; // 开始的序号
    int line = 6;       // 行数
    int itemRadius;
    int todayIndex; // 该月是今天的序号
    private Calendar today;

    int dateWidth;
    int dateHeight;

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        today = Calendar.getInstance();

        wordPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        wordPaint.setTextSize(sp2px(16));

        itemBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        itemBgPaint.setStyle(Paint.Style.FILL);

        testPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        testPaint.setStyle(Paint.Style.STROKE);
        testPaint.setStrokeWidth(1);
        testPaint.setColor(Color.YELLOW);

        itemRadius = dp2px(DEFAULT_RADIUS);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        setMeasuredDimension();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        dateWidth = getMeasuredWidth()/7;

        dateHeight = getMeasuredHeight()/line;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        drawHeader(canvas);
        drawItem(canvas);



    }

    private void drawItem(Canvas canvas) {
        for(int i=0;i<data.size();i++){
            drawDate(canvas,data.get(i));
        }
    }

    private void drawDate(Canvas canvas, DateBean dateBean) {
        drawBackground(canvas,dateBean);
        drawWord(canvas, dateBean);
//        drawBound(canvas,dateBean);
    }

    private void drawBound(Canvas canvas, DateBean dateBean) {
        int x = dateBean.getX()*dateWidth;
        int y = dateBean.getY()*dateHeight;

        canvas.drawCircle(x,y,10,itemBgPaint);
    }

    private void drawBackground(Canvas canvas, DateBean dateBean) {
        int x = dateBean.x*dateWidth+dateWidth/2;
        int y = dateBean.y*dateHeight+dateHeight/2;

        if(dateBean.getStyle() == DateBean.STYLE_PASSED){
            itemBgPaint.setColor(Color.GRAY);
        }else if(dateBean.getStyle() == DateBean.STYLE_TODAY){
            itemBgPaint.setColor(Color.RED);
        }else if(dateBean.getStyle() == DateBean.STYLE_FUTURE){
            itemBgPaint.setColor(Color.GREEN);
        }
        canvas.drawCircle(x,y,itemRadius,itemBgPaint);
    }

    private void drawWord(Canvas canvas, DateBean dateBean) {
        String word = dateBean.date.get(Calendar.DAY_OF_MONTH)+"";
        int width = (int) wordPaint.measureText(word);
        Paint.FontMetricsInt fontMetricsInt = wordPaint.getFontMetricsInt();
        int baseline = fontMetricsInt.ascent-fontMetricsInt.top;
        int x = dateBean.x*dateWidth+(dateWidth-width)/2;
        int y = (int) (dateBean.y*dateHeight+(dateHeight+wordPaint.getTextSize())/2 - baseline);
        canvas.drawText(word,x,y,wordPaint);
    }

    public void setData(List<DateBean> data){
        this.data = data;
        Calendar firstDay = data.get(0).getDate();
        startIndex = firstDay.get(Calendar.DAY_OF_WEEK) == 1?6:firstDay.get(Calendar.DAY_OF_WEEK)-2;

        for(int i=0;i<data.size();i++){
            DateBean date = data.get(i);
            setDateStyle(date);
            setDateXY(date,i);
        }

        // 计算行数
        calculateLine();
        // 计算今天的index，判断与今天的关系
        calculateToday();
    }

    private void calculateToday() {
        Calendar startDate = data.get(0).getDate();
        int year = getGap(startDate,Calendar.YEAR);
        if(year > 0){// 今年以后
            todayIndex = -2;
        }else if(year < 0){// 今年以前
            todayIndex = -1;
        }else{
            int month = getGap(startDate,Calendar.MONTH);
            if(month > 0){// 本月以后
                todayIndex = -2;
            }else if(month < 0){// 本月以前
                todayIndex = -1;
            }else {
                todayIndex = today.get(Calendar.DAY_OF_MONTH)-1;
            }
        }

        for(int i=0;i<data.size();i++){
            if(todayIndex == -2){
                data.get(i).setStyle(DateBean.STYLE_FUTURE);
            }else if(todayIndex == -1){
                data.get(i).setStyle(DateBean.STYLE_PASSED);
            }else{
                if(i<todayIndex){
                    data.get(i).setStyle(DateBean.STYLE_PASSED);
                }else if(i==todayIndex){
                    data.get(i).setStyle(DateBean.STYLE_TODAY);
                }else if(i>todayIndex){
                    data.get(i).setStyle(DateBean.STYLE_FUTURE);
                }
            }
        }
    }

    private int getGap(Calendar calendar,int field){
        int result = calendar.get(field)-today.get(field);
        if(result>0){// 2019
            return 1;
        }else if(result == 0){
            return 0;
        }else if(result<0){// 2016
            return -1;
        }
        return 0;
    }

    private void setDateXY(DateBean date, int i) {
        date.setX((i+startIndex)%7);
        date.setY((i+startIndex)/7);
    }

    private void setDateStyle(DateBean date) {
        // 找出index

        int result = date.getDate().compareTo(today);
        if(result == 1){
            result = 1;
        }

        date.setStyle(result);
    }

    private void calculateLine(){
        line = (int) Math.ceil((startIndex+data.size()+1)*1.0f/7);
    }

    protected int dp2px(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    protected int sp2px(int sp) {
        float density = getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * density + 0.5);
    }

}
