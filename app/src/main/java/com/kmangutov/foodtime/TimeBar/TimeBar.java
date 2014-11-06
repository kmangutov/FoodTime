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
    //protected float extremitySelectionThreshold = 0.2f;
    protected float mDesiredDragSectionHeight = 50f;

    protected float getDragSectionHeight(TimeSlot slot) {
        float slotHeight = (slot.end - slot.start) * getHeight();
        float halfSlotHeight = slotHeight / 2;

        return Math.min(halfSlotHeight, mDesiredDragSectionHeight);
    }

    // is none of this timeslot selected, the top, the middle, or the bottom? yFrac is absolute
    protected SlotSelection touchYFracToSelection(TimeSlot slot, float yFrac) {

        SlotSelection.Location location = SlotSelection.Location.NONE;

        if(yFrac < slot.start || yFrac > slot.end)
            return new SlotSelection(slot, location);

        location = SlotSelection.Location.MIDDLE;
        float selectionFraction = (yFrac - slot.start) / (slot.end - slot.start);

        float dragSectionHeight = getDragSectionHeight(slot);
        float slotHeight = (slot.end - slot.start) * getHeight();
        float selectionY = selectionFraction * slotHeight;

        /*if(selectionFraction <= extremitySelectionThreshold)
            location = SlotSelection.Location.TOP;
        else if(selectionFraction >= 1 - extremitySelectionThreshold)
            location = SlotSelection.Location.BOTTOM;
        */

        if(selectionY <= dragSectionHeight)
            location = SlotSelection.Location.TOP;
        else if(selectionY + dragSectionHeight >=  slotHeight)
            location = SlotSelection.Location.BOTTOM;


        System.out.println("HANDLE: " + location.name());

        return new SlotSelection(slot, location);
    }

    protected LocalTime fractionToTime(float fraction) {

        //prevents app from crashing for extreme upper and lower bounds
        if(fraction < 0)
            return new LocalTime(0,0);
        else if(fraction > 1)
        {
            return new LocalTime(23,59);
        }
        else {
            int hours = (int) Math.floor(fraction * 24);
            int minutes = (int) (int) Math.floor(((fraction * 24) % 60 % 1) * 60);
            return new LocalTime(hours, minutes);
        }
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

    //method to draw time in XX:YY format to left of upper and lower handles of timeslots
    protected void drawFloatingTime(TimeSlot slot, float y1, float y2, float x1, Canvas canvas) {

        Paint paint = new Paint();
        paint.setAlpha(255);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(25);

        //modify to scale!!!
        float left_bound = x1 - 150;
        float right_bound = x1 - 30;

        LocalTime start = fractionToTime(slot.start);
        int start_hour = start.getHourOfDay();
        int start_minute = start.getMinuteOfHour();
        LocalTime end = fractionToTime(slot.end);
        int end_hour = end.getHourOfDay();
        int end_minute = end.getMinuteOfHour();

        String start_str = start_hour +":"+ start_minute;
        String end_str = end_hour +":"+ end_minute;
        canvas.drawText(start_str, left_bound, y1, paint);
        canvas.drawText(end_str, left_bound, y2, paint);
    }
    //method to clear time in XX:YY format to left of upper and lower handles of timeslots
    protected void clearFloatingTime(TimeSlot slot, Canvas canvas) {

        Paint paint = new Paint();
        paint.setAlpha(255);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);

        //draw rectangle over text if finger released
        int left_bound = getBarX() - 150;
        int right_bound = getBarX() - 30;
        float y1 = getHeight()*slot.start;
        float y2 = getHeight()*slot.end;
        canvas.drawRect(left_bound, y1 - 30, right_bound, y1, paint);
        canvas.drawRect(left_bound, y2 - 30, right_bound, y2, paint);
        return;
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

        drawFloatingTime(slot, y1, y2, x1, canvas);

        canvas.drawRect(
                x1,
                y1,
                x2,
                y2,
                paint);

        paint.setColor(Color.BLACK);

        float dragSectionHeight = getDragSectionHeight(slot);
        float extremitySelectionThreshold = dragSectionHeight / slot.end - slot.start;

        //draw top and bottom handle
        canvas.drawRect(
                x1,
                y1,
                x2,
                y1 + dragSectionHeight,
                paint);

        canvas.drawRect(
                x1,
                y2 - dragSectionHeight,
                x2,
                y2,
                paint);

        if(clear_time)
        {
            clearFloatingTime(slot, canvas);
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

    boolean clear_time = false;

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

                //System.out.println("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:

                selected = null;
                //selected = touchYFracToTimeSlot(yFrac);

                /*  unselect selected timeslot */
                clear_time = true;
                System.out.println("ACTION_UP");
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
                //System.out.println("ACTION_MOVE");
                break;

            case MotionEvent.ACTION_CANCEL:
                //System.out.println("ACTION_CANCEL");
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