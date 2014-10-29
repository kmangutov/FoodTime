package com.kmangutov.foodtime;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kmangutov on 10/29/14.
 */
public class TimeBar extends View {

    public TimeBar(Context context) {
        super(context);
    }

    public TimeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFF33B5E5);
        paint.setStrokeWidth(4);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        //paint.setColor(0x777777);
        canvas.drawLine(0, 0, getWidth(), getHeight(), paint);
    }
}
