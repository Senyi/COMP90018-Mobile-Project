package com.olderlycare.mobile.olderlycare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by aquila on 4/10/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context arg0, Intent arg1){
        Toast.makeText(arg0,"sample text", Toast.LENGTH_SHORT).show();
    }
}