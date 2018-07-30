package com.yxj.practise.test.antboard;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Author:  Yxj
 * Time:    2018/7/28 上午8:56
 * -----------------------------------------
 * Description:
 */
public class DashBoardView extends View {

    Paint bgPaint;
    Paint indexArcPaint;
    int bgPaintColor = Color.WHITE;
    int scoreTextColor = Color.WHITE;
    int indexArcColor = Color.WHITE;
    int bgPaintAlpha = 60;
    int bgPaintLightAlpha = 120;

    int score = 590;// 分数
    int lastScore = 350;
    String rank = "信用极好";

    private TextPaint indexPaint;
    private TextPaint scorePaint;
    int indexTextSize = 35; // index文字大小
    int scoreTextSize = 200; // index文字大小

    private int bgStartAngle, bgSweepAngle;
    private int outerWidth = 10;    // 外圆线宽
    private int innerWidth = 35;    // 内圆宽度
    private int radiuGap = 30;      // 圆弧间距
    private int wordsGap = 20;      // 字与圆的间距
    private int sweepGap;           // 角度间隔，计算出来的
    private int indexSegment = 4;   // index的中间分成几份

    private String[] indexArray;
    private String[] segmentArray;

    private int mWidth, mHeight;
    private int paddingTop;
    private int paddingBottom;


    public DashBoardView(Context context) {
        super(context);
    }

    public DashBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DashBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(bgPaintColor);
        bgPaint.setAlpha(bgPaintAlpha);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeJoin(Paint.Join.ROUND);
        bgPaint.setStrokeWidth(outerWidth);

        indexPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        indexPaint.setColor(bgPaintColor);
        indexPaint.setTextSize(indexTextSize);

        indexArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        indexArcPaint.setColor(indexArcColor);
        indexArcPaint.setStyle(Paint.Style.STROKE);
        indexArcPaint.setStrokeJoin(Paint.Join.ROUND);
        indexArcPaint.setStrokeWidth(outerWidth);

        scorePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        scorePaint.setColor(scoreTextColor);
        scorePaint.setTextSize(scoreTextSize);

        bgStartAngle = 160;
        bgSweepAngle = 220;

        indexArray = new String[]{"350", "550", "600", "650", "700", "950"};
        segmentArray = new String[]{"较差", "中等", "良好", "优秀", "极好"};

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("yxj", "onDraw");

        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.save();

        //画背景外圆弧
        drawOuterArc(canvas);

        //画背景内圆盘
        drawInnerArc(canvas);

        //画背景刻度线
        drawIndex(canvas);

        canvas.restore();

        drawIndexArc(canvas);

//        canvas.drawLine(0,0,100,0,bgPaint);

        // 画表盘
        drawScore(canvas);
    }

    private void drawScore(Canvas canvas) {
        String word = score + "";
        scorePaint.setTextSize(scoreTextSize);
        int scoreWidth = (int) scorePaint.measureText(word);
        int scoreStartX = -scoreWidth / 2;
        int scoreStartY = 0;
        canvas.drawText(score + "", scoreStartX, scoreStartY, scorePaint);

        scorePaint.setTextSize(80);
        int rankWidth = (int) scorePaint.measureText(rank);
        int rankStartX = -rankWidth / 2;
        int rankStartY = 100;
        canvas.drawText(rank, rankStartX, rankStartY, scorePaint);

        String time = "评估时间：2018-07-28";
        int timeWidth = (int) indexPaint.measureText(time);
        int timeStartX = -timeWidth / 2;
        int timeStartY = (int) (rankStartY + scorePaint.getTextSize() / 2 + indexPaint.getTextSize() / 2 + 10);
        canvas.drawText(time, timeStartX, timeStartY, indexPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("yxj", "onSizeChanged");

        mWidth = w;
        mHeight = h;

        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("yxj", "onLayout");

    }

    private void drawOuterArc(Canvas canvas) {
        int radius = calculateOuterRadius();
        int left = -radius;
        int right = radius;
        int top = -radius;
        int bottom = radius;

        Path outerPath = new Path();
        RectF rectF = new RectF(left, top, right, bottom);

        bgPaint.setStrokeWidth(outerWidth);

        outerPath.addArc(rectF, bgStartAngle, bgSweepAngle);
        canvas.drawPath(outerPath, bgPaint);
    }

    private void drawIndexArc(Canvas canvas) {
        int radius = calculateOuterRadius();
        int left = -radius;
        int right = radius;
        int top = -radius;
        int bottom = radius;

        Path outerPath = new Path();
        RectF rectF = new RectF(left, top, right, bottom);

        int sweepAngle = calculateIndexSweepAngle();

        outerPath.addArc(rectF, bgStartAngle, sweepAngle);
        canvas.drawPath(outerPath, indexArcPaint);
    }

    private int calculateIndexSweepAngle() {
        int index = 0;
        int sweepAngle = 0;
        for (int i = 0; i < indexArray.length; i++) {
            if (Integer.parseInt(indexArray[i]) > score) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            return 0;
        }
        int start = Integer.parseInt(indexArray[index - 1]);
        int end = Integer.parseInt(indexArray[index]);
        sweepAngle = (index - 1) * (sweepGap * 2) + (int) Math.round((score - start) * 1f / (end - start) * (sweepGap * 2) + 0.5);
        return sweepAngle;
    }

    private void drawInnerArc(Canvas canvas) {
        int radius = calculateOuterRadius() - (radiuGap + outerWidth / 2 + innerWidth / 2);
        int left = -radius;
        int right = radius;
        int top = -radius;
        int bottom = radius;

        Path outerPath = new Path();
        RectF rectF = new RectF(left, top, right, bottom);

        outerPath.addArc(rectF, bgStartAngle, bgSweepAngle);
        bgPaint.setStrokeJoin(Paint.Join.ROUND);
        bgPaint.setStrokeWidth(innerWidth);
        canvas.drawPath(outerPath, bgPaint);
    }

    private void drawIndex(Canvas canvas) {
        String[] array = new String[indexArray.length + segmentArray.length];
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                array[i] = indexArray[i / 2];
            } else {
                array[i] = segmentArray[(i - 1) / 2];
            }
        }

        sweepGap = Math.round(bgSweepAngle / (indexArray.length + segmentArray.length - 1));
        int innerSweepGap = Math.round(bgSweepAngle / (indexArray.length - 1)) / indexSegment;

        int innerRadius = calculateOuterRadius() - (radiuGap + outerWidth / 2 + innerWidth / 2);

        int startAngle = -(90 + 180 - bgStartAngle);
        canvas.rotate(startAngle);

        for (int i = 0; i < array.length; i++) {
            canvas.rotate(i == 0 ? 0 : sweepGap);
            Rect bound = new Rect();
            indexPaint.getTextBounds(array[i], 0, array[i].length(), bound);
            indexPaint.setAlpha(i % 2 == 0 ? bgPaintLightAlpha : bgPaintAlpha);
            float width = indexPaint.measureText(array[i]);
            int y = innerRadius - innerWidth / 2 - wordsGap - indexTextSize / 2;
            canvas.drawText(array[i], -width / 2, -y, indexPaint);

            // 画刻度线
            if (i % 2 == 0) {
                indexPaint.setStrokeWidth(5);
                indexPaint.setAlpha(bgPaintLightAlpha);
                canvas.drawLine(0, -(innerRadius + innerWidth / 2), 0, -(innerRadius - innerWidth / 2), indexPaint);

                // 画小刻度线
                if (i != array.length - 1) {
                    indexPaint.setStrokeWidth(2);
                    indexPaint.setAlpha(bgPaintAlpha);
                    for (int j = 1; j < indexSegment; j++) {
                        canvas.rotate(innerSweepGap);
                        canvas.drawLine(0, -(innerRadius + innerWidth / 2), 0, -(innerRadius - innerWidth / 2), indexPaint);
                    }
                    canvas.rotate(-innerSweepGap * (indexSegment - 1));
                }
            }
        }
    }

    /**
     * 计算外圆的宽度
     *
     * @return
     */
    private int calculateOuterRadius() {
        return (int) Math.round(Math.min(mHeight - paddingTop - paddingBottom, mWidth) * 1f / 2 + 0.5);
    }

    public void setScore(int score) {
        this.score = score;

        setRank(score);

        ValueAnimator animator = ValueAnimator.ofInt(lastScore, score);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                DashBoardView.this.score = value;
                invalidate();
            }
        });
        animator.start();
    }

    public void setRank(int score) {
        int index = 0;
        for (int i = 0; i < indexArray.length; i++) {
            if (Integer.parseInt(indexArray[i]) > score) {
                index = i;
                break;
            }
        }
        rank = new String().format("信用%s", segmentArray[index - 1]);
    }
}
