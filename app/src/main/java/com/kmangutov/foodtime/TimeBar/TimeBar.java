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
    SlotSelection selected = null;

    public TimeBar(Context context) {
        super(context);
    }

    public TimeBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected int mBarWidth = 180;
    protected int mBarLeftPadding;

    // determines what fraction of TimeSlot is required to be clicked to identify top or bottom
    protected float extremitySelectionThreshold = 0.2f;

    // is none of this timeslot selected, the top, the middle, or the bottom? yFrac is absolute
    protected SlotSelection touchYFracToSelection(TimeSlot slot, float yFrac) {

        SlotSelection.Location location = SlotSelection.Location.NONE;

        if(yFrac < slot.start || yFrac > slot.end)
            return new SlotSelection(slot, location);

        location = SlotSelection.Location.MIDDLE;
        float selectionFraction = (yFrac - slot.start) / (slot.end - slot.start);
        if(selectionFraction <= extremitySelectionThreshold)
            location = SlotSelection.Location.TOP;
        else if(selectionFraction >= 1 - extremitySelectionThreshold)
            location = SlotSelection.Location.BOTTOM;

        System.out.println("HANDLE: " + location.name());

        return new SlotSelection(slot, location);
    }

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
            drawTimeSlot(canvas, slot, paint);
        }
    }

    protected void drawTimeSlot(Canvas canvas, TimeSlot slot, Paint paint) {

        if(selected != null && slot == selected.slot) {
            paint.setColor(Color.CYAN);
        } else {
            paint.setColor(Color.BLUE);
        }

        float y1 = getHeight()*slot.start;
        float y2 = getHeight()*slot.end;
        float slotH = y2 - y1;

        float x1 = getBarX();
        float x2 = getBarX() + getBarWidth();

        canvas.drawRect(
                x1,
                y1,
                x2,
                y2,
                paint);

        paint.setColor(Color.BLACK);

        //draw top and bototm handle
        canvas.drawRect(
                x1,
                y1,
                x2,
                y1 + slotH * extremitySelectionThreshold,
                paint);

        canvas.drawRect(
                x1,
                y2 - slotH * extremitySelectionThreshold,
                x2,
                y2,
                paint);

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

    protected SlotSelection touchYFracToTimeSlot(float fraction) {
        // TODO: loop through mTimeSlots and check if we selected a timeslot and return it
        // return null if we didn't

        for(TimeSlot slot : mTimeSlots) {
            if( slot.start <= fraction &&
                slot.end >= fraction)
                return touchYFracToSelection(slot, fraction);
        }

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
                    TimeSlot selectedSlot = new TimeSlot(yFrac, yFrac);
                    mTimeSlots.add(selectedSlot);

                    selected = new SlotSelection(selectedSlot,
                            SlotSelection.Location.BOTTOM);
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

                switch(selected.location)
                {
                    case TOP:
                        selected.slot.start = yFrac;
                        break;
                    case BOTTOM:
                        selected.slot.end = yFrac;
                        break;
                    case MIDDLE:
                        System.out.println("MIDDLE SELECTED");
                        break;
                }

                break;


        }
        this.invalidate();

        return true;
    }
}

class SlotSelection {
    public TimeSlot slot;
    public Location location;

    public enum Location {
        NONE,
        TOP,
        MIDDLE,
        BOTTOM
    };

    public SlotSelection(TimeSlot slot, Location location) {
        this.slot = slot;
        this.location = location;
    }
}
