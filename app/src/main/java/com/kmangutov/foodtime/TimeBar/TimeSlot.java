package com.kmangutov.foodtime.TimeBar;

import android.graphics.Color;

import org.joda.time.LocalTime;

public class TimeSlot {

    protected LocalTime mBeginTime;
    protected LocalTime mEndTime;

    // these are fractions 0 to 1 mapping 0 hrs to 24 hrs
    public float start;
    public float end;

    public int color = Color.BLUE;

    public TimeSlot() {

    }

    public TimeSlot(float start, float end) {
        this.start = start;
        this.end = end;
    }

    public void setColor(int color) { this.color = color; }

    public LocalTime getBeginTime() {
        return mBeginTime;
    }

    public LocalTime getEndTime() {
        return mEndTime;
    }

    public void setBeginTime(LocalTime time) {
        mBeginTime = time;
    }

    public void setEndTime(LocalTime time) {
        mEndTime = time;
    }
}
