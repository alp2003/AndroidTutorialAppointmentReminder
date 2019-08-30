package com.android.androidtutorialappointmentreminder.Models;

public class Appointment {
    private String mName;
    private String mType;
    private String mMonthDate;
    private int mDayDate;
    private int mYearDate;
    private int mHourTime;
    private int mMinuteTime;
    private String mAMorPMTime;

    public Appointment() {
    }

    public Appointment(final String name, final String type, final String monthDate, final int dayDate,
            final int yearDate, final int hourTime,
            final int minuteTime, final String AMorPMTime) {
        mName = name;
        mType = type;
        mMonthDate = monthDate;
        mDayDate = dayDate;
        mYearDate = yearDate;
        mHourTime = hourTime;
        mMinuteTime = minuteTime;
        mAMorPMTime = AMorPMTime;
    }

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(final String type) {
        mType = type;
    }

    public String getMonthDate() {
        return mMonthDate;
    }

    public void setMonthDate(final String monthDate) {
        mMonthDate = monthDate;
    }

    public int getDayDate() {
        return mDayDate;
    }

    public void setDayDate(final int dayDate) {
        mDayDate = dayDate;
    }

    public int getYearDate() {
        return mYearDate;
    }

    public void setYearDate(final int yearDate) {
        mYearDate = yearDate;
    }

    public int getHourTime() {
        return mHourTime;
    }

    public void setHourTime(final int hourTime) {
        mHourTime = hourTime;
    }

    public int getMinuteTime() {
        return mMinuteTime;
    }

    public void setMinuteTime(final int minuteTime) {
        mMinuteTime = minuteTime;
    }

    public String getAMorPMTime() {
        return mAMorPMTime;
    }

    public void setAMorPMTime(final String AMorPMTime) {
        this.mAMorPMTime = AMorPMTime;
    }
}
