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

import com.kmangutov.foodtime.R;

import org.joda.time.LocalTime;

import java.util.ArrayList;

/**
 * Created by kmangutov on 10/29/14.
 */
public class TimeBar extends View {

    ArrayList<TimeSlot> mTimeSlots = new ArrayList<TimeSlot>();
    SlotSelection selected = null;

    protected int fgColor = 0;
    protected int fg2Color = 0;
    protected int bgColor = 0;

    public TimeBar(Context context) {
        super(context);
    }

    public TimeBar(Context context, AttributeSet attrs) {

        super(context, attrs);
        fgColor =
                context.getResources().getColor(R.color.color_foreground);
        bgColor =
                context.getResources().getColor(R.color.color_background);
        fg2Color =
                context.getResources().getColor(R.color.color_foreground2);

        //addDummyTime(3, 15);
    }

    protected int mBarWidth = 180;
    protected int mBarRightPadding = 60;

    // determines what fraction of TimeSlot is required to be clicked to identify top or bottom
    //protected float extremitySelectionThreshold = 0.2f;
    protected float mDesiredDragSectionHeight = 50f;

    public TimeSlot getTimeSlot() {

        if(mTimeSlots == null || mTimeSlots.size() == 0)
            return null;
        return mTimeSlots.get(0);
    }

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

    protected float timeToFraction(LocalTime time) {

        return time.getHourOfDay()/24.0f + time.getMinuteOfHour()/60.0f/24.0f;
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

    public void addTimeSlot(TimeSlot slot) {

        mTimeSlots.add(slot);
    }

    public void addDummyTime(int hour, int minute, int color) {

        LocalTime start = new LocalTime(hour, minute);
        LocalTime end = new LocalTime(hour + 2, minute + 30);
        TimeSlot slot = new TimeSlot(timeToFraction(start), timeToFraction(end));
        slot.setColor(fg2Color);//color);

        addTimeSlot(slot);
    }

    protected int getBarWidth() {
        return Math.min(getWidth(), mBarWidth);
    }

    protected int getBarX() {
        return getWidth() - getBarWidth() - mBarRightPadding;
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
        paint.setColor(fgColor);
        paint.setStrokeWidth(4);

        for(TimeSlot slot : mTimeSlots) {
            drawTimeSlot(canvas, slot, paint);
        }

    }

    protected void drawPreciseTime(Canvas canvas,
                                   TimeSlot slot,
                                   Paint paint,
                                   float x,
                                   float y1,
                                   float y2) {




        paint.setTextSize(36);
        Paint.FontMetrics metrics = paint.getFontMetrics();
        float yOffset = metrics.ascent + metrics.descent;

        float myX = x - 100;
        float myWidth = 95;
        float yPadding = 5;

        String startStr = fracToString(slot.start);
        String endStr = fracToString(slot.end);


        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(fg2Color);
        paint.setAlpha(255);
        paint.setStrokeWidth(4);

        System.out.println("yOffset: " + yOffset);


        canvas.drawRect(myX - yPadding, y1 - yPadding, myX + myWidth, y1 - yOffset + yPadding, paint);
        canvas.drawRect(myX - yPadding, y2 - yPadding, myX + myWidth, y2 - yOffset + yPadding, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
        paint.setColor(fg2Color);

        canvas.drawText(startStr, myX, y1 - yOffset, paint);
        canvas.drawText(endStr, myX, y2 - yOffset, paint);
    }

    protected void drawTimeSlot(Canvas canvas, TimeSlot slot, Paint paint) {

        float y1 = getHeight()*slot.start;
        float y2 = getHeight()*slot.end;
        float slotH = y2 - y1;

        float x1 = getBarX();
        float x2 = getBarX() + getBarWidth();


        if(selected != null && slot == selected.slot) {
            drawPreciseTime(canvas, slot, paint, x1, y1, y2);
            paint.setColor(fgColor);
        } else {
            paint.setColor(fg2Color);//slot.color);//Color.BLUE);
        }


        //drawFloatingTime(slot, y1, y2, x1, canvas);

        canvas.drawRect(
                x1,
                y1,
                x2,
                y2,
                paint);


        float dragSectionHeight = getDragSectionHeight(slot);
        float extremitySelectionThreshold = dragSectionHeight / slot.end - slot.start;

        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

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



        drawAffordanceBars(x1, y1, x2, y1 + dragSectionHeight, canvas, paint);

        drawAffordanceBars(x1, y2 - dragSectionHeight, x2, y2, canvas, paint);

       /* if(clear_time)
        {
            clearFloatingTime(slot, canvas);
        }*/
    }

    protected void drawAffordanceBars(float x1, float y1, float x2, float y2, Canvas canvas, Paint paint) {

        float w = x2 - x1;
        float h = y2 - y1;

        float myX1 = x1 + w/5.0f;
        float myX2 = x2 - w/5.0f;

        float myY1 = y1 + w/5.0f;
        float myY2 = y2 - w/5.0f;

        float myDY = (myY2 - myY1)/3;

        paint.setColor(Color.BLACK);

        for(int i = 0; i < 3; i++) {
            float realY = myY1 + myDY * i;
            canvas.drawLine(myX1, realY, myX2, realY, paint);
        }
    }

    // convert fraction of timebar to hour:minute
    protected String fracToString(float frac) {

        int startHour = fractionToTime(frac).getHourOfDay();
        int startMinute = fractionToTime(frac).getMinuteOfHour();
        startHour = (startHour == 12)?12:(startHour % 12);

        return startHour + ":" + String.format("%02d", startMinute);
    }

    protected void drawTicks(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAlpha(255);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        float x = getBarX() + getBarWidth() + 10;

        for(int i = 0; i < 24; i++) {

            float y = i*getHeight()/24;

            int timeVal = (i == 12)?12:(i % 12);

            if(i % 3 == 0) {
                paint.setTextSize(32);
                paint.setColor(Color.BLACK);
                canvas.drawLine(getBarX(), y, getBarX() + getBarWidth(), y, paint);
            }
            else
                paint.setTextSize(20);

            String str = String.format("%02d", timeVal);

            //canvas.drawText(str, getBarX() - 40, i*getHeight()/24, paint);
            canvas.drawText(str, x, y, paint);
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
                    if(mTimeSlots.size() >= 1)
                        return true;
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

                if(selected == null)
                    return true;
                switch(selected.location)
                {
                    case TOP:

                        if(yFrac < selected.slot.end)
                            selected.slot.start = yFrac;
                        break;
                    case BOTTOM:
                        if(yFrac > selected.slot.start)
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