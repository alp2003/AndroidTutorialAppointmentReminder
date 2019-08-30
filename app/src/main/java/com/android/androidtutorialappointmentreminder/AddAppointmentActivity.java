package com.android.androidtutorialappointmentreminder;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;

public class AddAppointmentActivity extends AppCompatActivity {



    private TextView mTextViewDate;
    private TextView mTextViewTime;



    private int mYear;
    private int mMonth;
    private int mDay;

    private int mHour;
    private int mMinute;

    private static final int DATE_DIALOG_ID = 999;
    private static final int TIME_DIALOG_ID = 998;

    private DatePickerDialog.OnDateSetListener mDatePickerListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(final DatePicker view, final int year, final int month, final int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;

            mTextViewDate.setText(new StringBuilder()
                    .append(mMonth+1)
                    .append("-")
                    .append(mDay)
                    .append("-")
                    .append(mYear).append(" "));


        }
    };
    private TimePickerDialog.OnTimeSetListener mTimePickerListener = new OnTimeSetListener() {
        @Override
        public void onTimeSet(final TimePicker view, final int hourOfDay, final int minute) {
            mHour = hourOfDay;
            mMinute = minute;

            mTextViewTime.setText(new StringBuilder()
                    .append(formatTime(mHour))
                    .append(":")
                    .append(formatTime(mMinute)));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        initControls();
        setCurrentDateAndTime();


    }

    private void initControls() {
        mTextViewDate = findViewById(R.id.add_appointment_text_view_date);
        mTextViewTime = findViewById(R.id.add_appointment_text_view_time);
    }

    private void setCurrentDateAndTime() {
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        mTextViewTime.setText(new StringBuilder()
                .append(formatTime(mHour))
                .append(":")
                .append(formatTime(mMinute)));

        mTextViewDate.setText(new StringBuilder()
                                  .append(mMonth+1)
                                  .append("-")
                                  .append(mDay)
                                  .append("-")
                                  .append(mYear).append(" "));

    }

    private String formatTime(final int timeUnit) {
        if (timeUnit >= 10){
            return timeUnit + "";
        }

        return "0" + timeUnit;
    }

    public void onAddAppointmentButton(View view) {
    }

    public void onCancelButton(View view) {
    }

    public void onEditTextViewDate(View view) {
        showDialog(DATE_DIALOG_ID);
    }

    public void onEditTextViewTime(View view) {
        showDialog(TIME_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(final int id) {
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDatePickerListener, mYear, mMonth, mDay);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, mTimePickerListener, mHour, mMinute, false);
        }
        return null;
    }





}
