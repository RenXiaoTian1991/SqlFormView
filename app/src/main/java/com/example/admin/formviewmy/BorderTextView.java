package com.example.admin.formviewmy;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 2016/9/20.
 */
public class BorderTextView extends TextView {

    /**
     * 四周是否带有边框【true:四周带有边框】【false:四周不带边框】
     */
    boolean borders = true;
    /**
     * 边框颜色
     */
    String textColor = "#ff000000";
    private Paint paint;
    private Paint rectPaint;

    public BorderTextView(Context context) {
        this(context, null);
    }

    public BorderTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public BorderTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // 获取自定义属性集
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.BorderTextView);
        // 是否设置全部边框，默认为false
        borders = typedArray.getBoolean(
                R.styleable.BorderTextView_layout_borders, false);
        typedArray.recycle();

        // 创建画笔
        paint = new Paint();
        // 获取该画笔颜色
        int color = paint.getColor();
        // 设置画笔颜色
        paint.setColor(Color.BLACK);

        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(3);
        rectPaint.setColor(Color.BLACK);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        // 如果borders为true，表示左上右下都有边框
//        canvas.drawLine(0, 0, 0, this.getHeight() - 1, paint);
//        canvas.drawLine(0, 0, this.getWidth() - 1, 0, paint);
//        canvas.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1,
//                this.getHeight() - 1, paint);
//        canvas.drawLine(0, this.getHeight() - 1, this.getWidth() - 1,
//                this.getHeight() - 1, paint);


        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), rectPaint);

    }

    public void setBorders(boolean isDraw){
        borders = isDraw;
        invalidate();
    }
}
