package com.kmangutov.foodtime.TimeBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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

    double selection = 0.0;
    double release = 0.0;

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAlpha(255);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        this.invalidate();


        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xFF33B5E5);
        paint.setStrokeWidth(4);

        int barTop = (int)(selection * getHeight());
	int barBottom = (int)(release * getHeight());
        canvas.drawRect(15, barTop, getWidth(), barBottom, paint);

	paint.setColor(0xFF23A5D5);
	canvas.drawText(selection, 15, barTop, paint);
	canvas.drawText(release, 15, barBottom, paint);
	
        this.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d("MOTIONEVENT", event.getX() + "," + event.getY());
                selection = event.getY()/getHeight();
                System.out.println("selection: " + selection);
		break;
	    case MotionEvent.ACTION_UP:
		Log.d("MOTIONEVENT", event.getX() + "," + event.getY());
		release = event.getY()/getHeight();
		System.out.println("release: " + release);
		break;
        }

        return true;
    }
}
