package com.olderlycare.mobile.olderlycare;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {


    public void onReceive (Context context, Intent intent){
        String msg = "Alarm message";
        msg = intent.getStringExtra("msg");
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
