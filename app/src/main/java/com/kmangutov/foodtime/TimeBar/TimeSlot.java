package com.kmangutov.foodtime.TimeBar;

import org.joda.time.LocalTime;

public class TimeSlot {

    protected LocalTime mBeginTime;
    protected LocalTime mEndTime;

    public TimeSlot() {

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
