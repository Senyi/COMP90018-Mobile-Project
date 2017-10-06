package com.olderlycare.mobile.olderlycare;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.*;

public class ScheduleSettingActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSetCalendar;

    Button btnOK;
    Button btnCancel;

    TextView txtNotificate;
    EditText edtSchedule;
    Calendar calendar;

    String timeFormat;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_sche);
        calendar = Calendar.getInstance();
        btnSetCalendar = (Button) findViewById(R.id.btnSetClock);
        btnSetCalendar.setOnClickListener(this);
        txtNotificate = (TextView) findViewById(R.id.txtClockNotification);
        btnOK = (Button)findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        edtSchedule = (EditText) findViewById(R.id.edtSchedule);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.btnSetClock)
        {
            Toast.makeText(this,"btn set clock",Toast.LENGTH_LONG).show();
            calendar.setTimeInMillis(System.currentTimeMillis());
            int mHour = calendar.get(Calendar.HOUR_OF_DAY);
            int mMinute = calendar.get(Calendar.MINUTE);
            // Show the calendar view to set the alarm
            new TimePickerDialog(ScheduleSettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    //Initial the calendar with current time
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,minute);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);
//                    //Create the intent for the alarm
//                    Intent intent = new Intent(ScheduleSettingActivity.this, AlarmReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(ScheduleSettingActivity.this, 0, intent, 0);
//                    //Set up the alarm
//                    AlarmManager am;
//                    am = (AlarmManager)getSystemService(ALARM_SERVICE);
//                    am.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                    timeFormat =  format(hour) + ":" + format(minute);
                    String txt = "Notificate at " + timeFormat;
                    txtNotificate.setText(txt);
                }
            }, mHour, mMinute, true).show();
        }
        if (v.getId() == R.id.btnOK){
            Intent intent = new Intent(ScheduleSettingActivity.this,AlarmReceiver.class);
            intent.putExtra("msg",edtSchedule.getText().toString());
            int flag = (int)System.currentTimeMillis();
            PendingIntent pendingIntent = PendingIntent.getBroadcast(ScheduleSettingActivity.this,flag,intent,0);
            AlarmManager am;
            am = (AlarmManager)getSystemService(ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
            //Toast.makeText(this,intent.getStringExtra("msg"),Toast.LENGTH_LONG).show();

            Intent intentToSchedule = new Intent(ScheduleSettingActivity.this,ScheduleActivity.class);
            intentToSchedule.putExtra("time",timeFormat);
            intentToSchedule.putExtra("am","AM");
            intentToSchedule.putExtra("sche",edtSchedule.getText().toString());
            startActivity(intentToSchedule);
        }
        if (v.getId() == R.id.btnCancel){

        }



    }
    private String format(int x)
    {
        String s = "" + x;
        if(s.length() == 1)
            s = "0" + s;
        return s;
    }

}
