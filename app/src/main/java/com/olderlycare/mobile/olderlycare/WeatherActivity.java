package com.olderlycare.mobile.olderlycare;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olderlycare.mobile.olderlycare.data.Channel;
import com.olderlycare.mobile.olderlycare.data.Item;
import com.olderlycare.mobile.olderlycare.service.YahooService;
import com.olderlycare.mobile.olderlycare.service.ServiceCallback;

public class WeatherActivity extends AppCompatActivity implements ServiceCallback{

    private ImageView weatherIcon;
    private TextView temperature;
    private TextView location;
    private TextView condition;

    private ImageView firstDayIcon;
    private TextView firstDayHigh;
    private TextView firstDayLow;
    private TextView firstDayDay;

    private ImageView secondDayIcon;
    private TextView secondDayHigh;
    private TextView secondDayLow;
    private TextView secondDayDay;

    private ImageView thirdDayIcon;
    private TextView thirdDayHigh;
    private TextView thirdDayLow;
    private TextView thirdDayDay;

    private ImageView fourthDayIcon;
    private TextView fourthDayHigh;
    private TextView fourthDayLow;
    private TextView fourthDayDay;

    private ImageView fifthDayIcon;
    private TextView fifthDayHigh;
    private TextView fifthDayLow;
    private TextView fifthDayDay;

    private ImageView sixthDayIcon;
    private TextView sixthDayHigh;
    private TextView sixthDayLow;
    private TextView sixthDayDay;

    private ImageView seventhDayIcon;
    private TextView seventhDayHigh;
    private TextView seventhDayLow;
    private TextView seventhDayDay;


    private YahooService service;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIcon = (ImageView)findViewById(R.id.todayIcon);
        temperature = (TextView)findViewById(R.id.Temp);
        location = (TextView)findViewById(R.id.Location);
        condition = (TextView)findViewById(R.id.Condition);

        firstDayIcon = (ImageView)findViewById((R.id.firstDayIcon));
        firstDayHigh = (TextView)findViewById(R.id.firstDayHigh);
        firstDayLow = (TextView)findViewById(R.id.firstDayLow);
        firstDayDay = (TextView)findViewById(R.id.firstDayDay);

        secondDayIcon = (ImageView)findViewById(R.id.secondDayIcon);
        secondDayHigh = (TextView)findViewById(R.id.secondDayHigh);
        secondDayLow = (TextView)findViewById(R.id.secondDayLow);
        secondDayDay = (TextView)findViewById(R.id.secondDayDay);

        thirdDayIcon = (ImageView)findViewById(R.id.thirdDayIcon);
        thirdDayHigh = (TextView)findViewById(R.id.thirdDayHigh);
        thirdDayLow = (TextView)findViewById(R.id.thirdDayLow);
        thirdDayDay = (TextView)findViewById(R.id.thirdDayDay);

        fourthDayIcon = (ImageView)findViewById(R.id.fourthDayIcon);
        fourthDayHigh = (TextView)findViewById(R.id.fourthDayHigh);
        fourthDayLow = (TextView)findViewById(R.id.fourthDayLow);
        fourthDayDay = (TextView)findViewById(R.id.fourthDayDay);

        fifthDayIcon = (ImageView)findViewById(R.id.fifthDayIcon);
        fifthDayHigh = (TextView)findViewById(R.id.fifthDayHigh);
        fifthDayLow = (TextView)findViewById(R.id.fifthDayLow);
        fifthDayDay = (TextView)findViewById(R.id.fifthDayDay);

        sixthDayIcon = (ImageView)findViewById(R.id.sixthDayIcon);
        sixthDayHigh = (TextView)findViewById(R.id.sixthDayHigh);
        sixthDayLow = (TextView)findViewById(R.id.sixthDayLow);
        sixthDayDay = (TextView)findViewById(R.id.sixthDayDay);

        seventhDayIcon = (ImageView)findViewById(R.id.seventhDayIcon);
        seventhDayHigh = (TextView)findViewById(R.id.seventhDayHigh);
        seventhDayLow = (TextView)findViewById(R.id.seventhDayLow);
        seventhDayDay = (TextView)findViewById(R.id.seventhDayDay);

        service = new YahooService(this);
        dialog =  new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Melbourne, Australia");
    }
    @Override
    public void serviceSuccess(Channel channel){
        dialog.hide();
        // retrieve info from JSON objects
        Item item = channel.getItem();

        // initiating icons
        int resourceID = getResources().getIdentifier("drawable/icon_"+ item.getCondition().getCode(),null, getPackageName());
        Drawable icon = getResources().getDrawable(resourceID,null);
        weatherIcon.setImageDrawable(icon);
        // 1ST forecast
        int foreResourceID1 = getResources().getIdentifier("drawable/icon_"+ item.getForecast().getCode(1),null, getPackageName());
        Drawable foreIcon1 = getResources().getDrawable(foreResourceID1,null);
        firstDayIcon.setImageDrawable(foreIcon1);
        firstDayDay.setText(item.getForecast().getDay(1));
        firstDayHigh.setText(item.getForecast().getTemperatureHigh(1)+"\u00B0"+channel.getUnits().getTemperature());
        firstDayLow.setText(item.getForecast().getTemperatureLow(1)+"\u00B0"+channel.getUnits().getTemperature());
        // 2ND forecast
        int foreResourceID2 = getResources().getIdentifier("drawable/icon_"+ item.getForecast().getCode(2),null, getPackageName());
        Drawable foreIcon2 = getResources().getDrawable(foreResourceID2,null);
        secondDayIcon.setImageDrawable(foreIcon2);
        secondDayDay.setText(item.getForecast().getDay(2));
        secondDayHigh.setText(item.getForecast().getTemperatureHigh(2)+"\u00B0"+channel.getUnits().getTemperature());
        secondDayLow.setText(item.getForecast().getTemperatureLow(2)+"\u00B0"+channel.getUnits().getTemperature());
        // 3RD forecast
        int foreResourceID3 = getResources().getIdentifier("drawable/icon_"+ item.getForecast().getCode(3),null, getPackageName());
        Drawable foreIcon3 = getResources().getDrawable(foreResourceID3,null);
        thirdDayIcon.setImageDrawable(foreIcon3);
        thirdDayDay.setText(item.getForecast().getDay(3));
        thirdDayHigh.setText(item.getForecast().getTemperatureHigh(3)+"\u00B0"+channel.getUnits().getTemperature());
        thirdDayLow.setText(item.getForecast().getTemperatureLow(3)+"\u00B0"+channel.getUnits().getTemperature());
        // 4TH forecast
        int foreResourceID4 = getResources().getIdentifier("drawable/icon_"+ item.getForecast().getCode(4),null, getPackageName());
        Drawable foreIcon4 = getResources().getDrawable(foreResourceID4,null);
        fourthDayIcon.setImageDrawable(foreIcon4);
        fourthDayDay.setText(item.getForecast().getDay(4));
        fourthDayHigh.setText(item.getForecast().getTemperatureHigh(4)+"\u00B0"+channel.getUnits().getTemperature());
        fourthDayLow.setText(item.getForecast().getTemperatureLow(4)+"\u00B0"+channel.getUnits().getTemperature());
        // 5TH forecast
        int foreResourceID5 = getResources().getIdentifier("drawable/icon_"+ item.getForecast().getCode(5),null, getPackageName());
        Drawable foreIcon5 = getResources().getDrawable(foreResourceID5,null);
        fifthDayIcon.setImageDrawable(foreIcon5);
        fifthDayDay.setText(item.getForecast().getDay(5));
        fifthDayHigh.setText(item.getForecast().getTemperatureHigh(5)+"\u00B0"+channel.getUnits().getTemperature());
        fifthDayLow.setText(item.getForecast().getTemperatureLow(5)+"\u00B0"+channel.getUnits().getTemperature());
        // 6TH forecast
        int foreResourceID6 = getResources().getIdentifier("drawable/icon_"+ item.getForecast().getCode(6),null, getPackageName());
        Drawable foreIcon6 = getResources().getDrawable(foreResourceID6,null);
        sixthDayIcon.setImageDrawable(foreIcon6);
        sixthDayDay.setText(item.getForecast().getDay(6));
        sixthDayHigh.setText(item.getForecast().getTemperatureHigh(6)+"\u00B0"+channel.getUnits().getTemperature());
        sixthDayLow.setText(item.getForecast().getTemperatureLow(6)+"\u00B0"+channel.getUnits().getTemperature());
        // 7TH forecast
        int foreResourceID7 = getResources().getIdentifier("drawable/icon_"+ item.getForecast().getCode(7),null, getPackageName());
        Drawable foreIcon7 = getResources().getDrawable(foreResourceID7,null);
        seventhDayIcon.setImageDrawable(foreIcon7);
        seventhDayDay.setText(item.getForecast().getDay(7));
        seventhDayHigh.setText(item.getForecast().getTemperatureHigh(7)+"\u00B0"+channel.getUnits().getTemperature());
        seventhDayLow.setText(item.getForecast().getTemperatureLow(7)+"\u00B0"+channel.getUnits().getTemperature());



        temperature.setText(item.getCondition().getTemperature()+"\u00B0"+channel.getUnits().getTemperature());
        condition.setText(item.getCondition().getDescription());

        location.setText(service.getLocation());

        setNotification(item);

    }

    @Override
    public void serviceFailure(Exception exception){
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void setNotification(Item item){
        /*
        // notification
        NotificationManager mNotificationManager =  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String id = "channel_01";
        CharSequence name = getString(R.string.app_name);
        String description = "Weather in "+service.getLocation()+" is "+item.getCondition().getDescription()+ ", "+item.getCondition().getTemperature();
        int importance = NotificationManager.IMPORTANCE_HIGH;

        Notification mnotification = new Notification.Builder(Context mcontext)
        */
        int resourceID = getResources().getIdentifier("drawable/icon_"+ item.getCondition().getCode(),null, getPackageName());
        Drawable icon = getResources().getDrawable(resourceID,null);
        CharSequence NotificationContent = "Weather in "+service.getLocation()+" is "+item.getCondition().getDescription()+ ", "+item.getCondition().getTemperature();
        //app icon now using a sun
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.icon_32).setContentTitle("OrderlyCare").setContentText(NotificationContent);
        Intent notificationIntent = new Intent(this, WeatherActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());


    }



}
