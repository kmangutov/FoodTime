package com.kmangutov.foodtime.TimeBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import org.joda.time.LocalTime;

import java.util.ArrayList;

/**
 * Created by kmangutov on 10/29/14.
 */
public class TimeBar extends View {

    ArrayList<TimeSlot> mTimeSlots = new ArrayList<TimeSlot>();
    TimeSlot selected = null;

    public TimeBar(Context context) {
        super(context);
    }

    public TimeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    double selection = 0.0;

    protected int mBarWidth = 180;
    protected int mBarLeftPadding;

    protected LocalTime fractionToTime(float fraction) {
        int hours = (int) Math.floor(fraction * 24);
        int minutes = (int) Math.floor( (fraction * 24 ) % 1 );

        return new LocalTime(hours, minutes);
    }

    protected int getBarWidth() {
        return Math.min(getWidth(), mBarWidth);
    }

    protected int getBarX() {
        return getWidth() - getBarWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);


        drawBar(canvas);
        drawTicks(canvas);
        drawTimeSlots(canvas);
    }

    protected void drawTimeSlots(Canvas canvas) {

        Paint paint = new Paint();
        paint.setAlpha(255);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);

        for(TimeSlot slot : mTimeSlots) {
            canvas.drawRect(
                    getBarX(),
                    getHeight()*slot.start,
                    getBarX() + getBarWidth(),
                    getHeight()*slot.end,
                    paint);
        }
    }

    protected void drawTicks(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAlpha(255);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);

        for(int i = 0; i < 24; i++) {

            String str = String.format("%02d", i);

            canvas.drawText(str, getBarX() - 30, 20 + i*getHeight()/24, paint);
        }
    }

    protected void drawBar(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAlpha(255);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);
        canvas.drawRect(getBarX(), 0, getBarX() + getBarWidth(), getHeight(), paint);
    }

    protected TimeSlot touchYFracToTimeSlot(float fraction) {
        // TODO: loop through mTimeSlots and check if we selected a timeslot and return it
        // return null if we didn't

        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float eventX = event.getX();
        float eventY = event.getY();

        if(eventX < getBarX() || eventX > getBarX() + getBarWidth())
            return false;

        float yFrac = eventY / getHeight();

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                /*  check if we clicked on an existing TimeSlot. if so select this timeslot
                    and begin manipulation of it.
                    somehow keep track whether top, middle or bottom is selected (change start value
                    vs change overall time vs change end value )

                    if it doesn't exist, create a new TimeSlot and select it */

                selected = touchYFracToTimeSlot(yFrac);

                if(selected == null) {
                    selected = new TimeSlot(yFrac, yFrac);
                    mTimeSlots.add(selected);
                }


                break;
            case MotionEvent.ACTION_UP:

                selected = null;

                /*  unselect selected timeslot */
                break;
            case MotionEvent.ACTION_MOVE:
                /*  either change start time (top selected), end time (end selected)
                    or both if middle is selected
                 */
                selected.end = yFrac;

                break;


        }
        this.invalidate();

        return true;
    }
}
