package com.kmangutov.foodtime.TimeBar;

/**
 * Created by kmangutov on 10/30/14.
 */
public class TimeSlot {
    protected int mHour = -1;
    protected int mMinutes = -1;

    public TimeSlot() {

    }

    public TimeSlot(int hours, int minutes) {
        mHour = hours;
        mMinutes = minutes;
    }
}
