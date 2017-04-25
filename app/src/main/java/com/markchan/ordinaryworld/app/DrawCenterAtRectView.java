package com.markchan.ordinaryworld.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mark on 17/1/19.
 */

public class DrawCenterAtRectView extends View {

    public DrawCenterAtRectView(Context context) {
        this(context, null);
    }

    public DrawCenterAtRectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawCenterAtRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    public DrawCenterAtRectView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private Paint mPaint;
    private Paint mBoundsPaint;

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mBoundsPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBoundsPaint.setColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        // --------------------------------------------------
        String text = "asdfghjkl";
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(120);
        mPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fm = mPaint.getFontMetrics();
        float textHeight = fm.descent - fm.ascent;

        // 绘制文字的矩形框范围
        RectF rectF = new RectF(200, 200, 800, 200 + textHeight);
        canvas.drawRect(rectF, mBoundsPaint);

        canvas.drawText(text, rectF.left + rectF.width() / 2, rectF.bottom - fm.descent, mPaint);
    }
}
