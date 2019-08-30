package com.android.androidtutorialappointmentreminder;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.androidtutorialappointmentreminder.Models.Appointment;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE_ADD_APPOINTMENT = 1;
    private ArrayList<Appointment> mAppointments = new ArrayList<>();

    private TableLayout mTableLayoutAppointment;
    private TextView mTextViewNameTable;
    private TextView mTextViewTypeTable;
    private TextView mTextViewDateTimeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        createSomeTestAppointment();


    }

    private void initControls() {
        mTableLayoutAppointment = findViewById(R.id.main_table_layout_show_appointment);
        mTextViewNameTable = findViewById(R.id.main_text_view_name_table);
        mTextViewTypeTable = findViewById(R.id.main_text_view_type_table);
        mTextViewDateTimeTable = findViewById(R.id.main_text_view_date_time_table);
    }

    private void createSomeTestAppointment() {
        mAppointments.add(new Appointment("Doctors Visit","Health", "Oct", 9, 2016, 9, 00, "AM"));
        mAppointments.add(new Appointment("Hair Cut appointment","Personal","Oct", 10, 2016,9,30,"AM"));
        mAppointments.add(new Appointment("Meeting with Accountant","Personal","Oct", 11, 2016,11,00,"AM"));
        mAppointments.add(new Appointment("Boss/HR Meeting","Work","Oct", 12, 2016,2,30,"PM"));
        mAppointments.add(new Appointment("Teacher Conference","School","Nov", 1, 2016,9,30,"AM"));
        mAppointments.add(new Appointment("Dentist For Son","Health","Nov", 1, 2016,9,30,"AM"));
        mAppointments.add(new Appointment("Dinner With Friends","Other","Nov", 1, 2016,9,30,"AM"));

        for(int i = 0; i < mAppointments.size(); i++){
            populateTable(i);
        }


    }

    private void populateTable(int position) {
        TableRow tableRow = new TableRow(this);
        TableRow.LayoutParams layoutParams = new LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        tableRow.setLayoutParams(layoutParams);

        TextView textViewName = new TextView(this);
        textViewName.setLayoutParams(mTextViewNameTable.getLayoutParams());
        textViewName.setGravity(mTextViewNameTable.getGravity());
        textViewName.setWidth(mTextViewNameTable.getWidth());
        textViewName.setTextAlignment(mTextViewNameTable.getTextAlignment());
        textViewName.setText(mAppointments.get(position).getName());

        TextView textViewType = new TextView(this);
        textViewType.setLayoutParams(mTextViewTypeTable.getLayoutParams());
        textViewType.setGravity(mTextViewTypeTable.getGravity());
        textViewType.setWidth(mTextViewTypeTable.getWidth());
        textViewType.setTextAlignment(mTextViewTypeTable.getTextAlignment());
        textViewType.setText(mAppointments.get(position).getType());

        TextView textViewDateTime = new TextView(this);
        textViewDateTime.setLayoutParams(mTextViewDateTimeTable.getLayoutParams());
        textViewDateTime.setGravity(mTextViewDateTimeTable.getGravity());
        textViewDateTime.setWidth(mTextViewDateTimeTable.getWidth());
        textViewDateTime.setTextAlignment(mTextViewDateTimeTable.getTextAlignment());
        textViewDateTime.setText(setToDateAndTime(mAppointments.get(position)));


        tableRow.addView(textViewName);
        tableRow.addView(textViewType);
        tableRow.addView(textViewDateTime);
        mTableLayoutAppointment.addView(tableRow, position + 1);

    }

    private String setToDateAndTime(final Appointment appointment) {
        long currentDateAndTime = System.currentTimeMillis();
        SimpleDateFormat formatDate = new SimpleDateFormat("MMM d, yyyy");

        String todayDate = formatDate.format(currentDateAndTime);
        String passDate = appointment.getMonthDate() + " " + appointment.getDayDate() + ", " + appointment.getYearDate();

        if (Objects.equals(todayDate, passDate)){
            return appointment.getHourTime() + ":" + appointment.getMinuteTime() + " " + appointment.getAMorPMTime();
        }

        return appointment.getMonthDate() + " " + appointment.getDayDate() + ", " + appointment.getYearDate();
    }

    public void onAddAppointment(View view) {
        Intent intentAddAppointment = new Intent(this, AddAppointmentActivity.class);
        startActivityForResult(intentAddAppointment, REQUEST_CODE_ADD_APPOINTMENT);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_APPOINTMENT && resultCode == RESULT_OK){
            String name = data.getStringExtra("name");
            String type = data.getStringExtra("type");
            String monthOfYear = data.getStringExtra("monthOfYear");
            int dayOfMonth = data.getIntExtra("dayOfMonth", 1);
            int year = data.getIntExtra("year",0);
            int hour = data.getIntExtra("hour",1);
            int minute = data.getIntExtra("minute", 1);
            String amOrPm = data.getStringExtra("AMorPM");

            mAppointments.add(new Appointment(name, type, monthOfYear, dayOfMonth, year, hour, minute, amOrPm));
            populateTable(mAppointments.size()-1);
        }
    }
}
