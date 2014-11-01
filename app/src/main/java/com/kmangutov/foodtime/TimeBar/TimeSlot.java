package com.kmangutov.foodtime.TimeBar;

import org.joda.time.LocalTime;

public class TimeSlot {

    protected LocalTime mBeginTime;
    protected LocalTime mEndTime;

    //these are fractions 0 to 1 mapping 0 hrs to 24 hrs
    public float start;
    public float end;

    public TimeSlot() {

    }

    public TimeSlot(float start, float end) {
        this.start = start;
        this.end = end;
    }

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
