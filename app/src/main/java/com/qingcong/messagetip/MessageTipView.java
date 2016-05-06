package com.qingcong.messagetip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenpengfei on 2016/5/6.
 */
public class MessageTipView extends View {

    /**
     *  画笔
     */
    private Paint mPaint;

    /**
     * text内容
     */
    private String number;

    /**
     *  数字距离圆点里面的间距
     */
    private int numberSpacing;

    /**
     *  字体大小
     */
    private int numberSize;

    /**
     *  圆点颜色
     */
    private int cricleColor;

    /**
     *  文字颜色
     */
    private int numberColor;

    private Rect numberRect = new Rect();

    public MessageTipView(Context context) {
        super(context);
    }

    public MessageTipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MessageTipView);
        number = typedArray.getString(R.styleable.MessageTipView_number);
        numberColor = typedArray.getColor(R.styleable.MessageTipView_numbercolor, Color.WHITE);
        cricleColor = typedArray.getColor(R.styleable.MessageTipView_criclecolor, Color.RED);
        numberSize = typedArray.getDimensionPixelSize(R.styleable.MessageTipView_numbersize, 20);
        numberSpacing = typedArray.getDimensionPixelSize(R.styleable.MessageTipView_numberspacing, 20);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(numberSize);
    }

    public MessageTipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mPaint.getTextBounds(number, 0, number.length(), numberRect);
        int viewWH = Math.max(numberRect.height(), numberRect.width()) + numberSpacing;
        setMeasuredDimension(viewWH, viewWH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制圆点
        mPaint.setColor(cricleColor);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredWidth() / 2, getMeasuredWidth() / 2, mPaint);
        //绘制数字
        mPaint.setColor(numberColor);
        //文字X轴移中心来设置距离左边的距离
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(number, getMeasuredWidth() / 2, getMeasuredHeight() / 2 + numberRect.height() / 2, mPaint);
    }
}
