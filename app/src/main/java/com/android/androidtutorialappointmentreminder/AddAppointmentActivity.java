package com.android.androidtutorialappointmentreminder;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;

public class AddAppointmentActivity extends AppCompatActivity {


    private static final String TAG = "AddAppointmentActivity";

    private TextView mTextViewDate;
    private TextView mTextViewTime;
    private EditText mEditTextName;
    private Spinner mSpinnerTaskType;



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
        mEditTextName = findViewById(R.id.add_appointment_edit_text_name);
        mSpinnerTaskType = findViewById(R.id.add_appointment_spinner_task_type);
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
        String name = mEditTextName.getText().toString();
        String taskType = mSpinnerTaskType.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)){
            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("type", taskType);

            intent.putExtra("monthOfYear", DisplayTheMonthInString(mMonth));
            intent.putExtra("dayOfMonth", mDay);
            intent.putExtra("year", mYear);

            intent.putExtra("hour", formatTheHour(mHour));
            intent.putExtra("minute", mMinute);
            intent.putExtra("AMorPM", amOrPm(mHour));

            setResult(RESULT_OK, intent);
            finish();
        }else{
            Toast.makeText(this, "Fill Task Name Please", Toast.LENGTH_SHORT).show();
        }
    }

    private String amOrPm(final int hour) {
        if (hour > 12)
            return "PM";
        return "AM";
    }

    private int formatTheHour(int hour) {
        if (hour > 12){
            hour -= 12;
        }
        return hour;
    }

    private String DisplayTheMonthInString(final int month) {
        switch (month){
            case 0:
                return "Jan";
            case 1:
                return "Feb";
            case 2:
                return "Mar";
            case 3:
                return "Apr";
            case 4:
                return "May";
            case 5:
                return "Jun";
            case 6:
                return "Jul";
            case 7:
                return "Aug";
            case 8:
                return "Sep";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";
        }

        return "";
    }

    public void onCancelButton(View view) {
        finish();
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
