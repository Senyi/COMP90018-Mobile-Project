package com.olderlycare.mobile.olderlycare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleActivity extends AppCompatActivity {

    //    private String[] times = new String[]{"08:00","12:30","18:00"};
//    private String[] ams = new String[]{"AM","PM","PM"};
//    private String[] sches = new String[]{
//            "schedule1 schedule1",
//            "schedule2 schedule2",
//            "schedule3 schedule3"};
    private ArrayList<String> times = new ArrayList<String>();
    private ArrayList<String> ams = new ArrayList<String>();;
    private ArrayList<String> sches = new ArrayList<String>();;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialContents();
        Intent intent = getIntent();
        if (intent.getStringExtra("time") != null)
        {
//            Toast.makeText(this, intent.getStringExtra("time"), Toast.LENGTH_SHORT).show();
            times.add(intent.getStringExtra("time"));
            ams.add(intent.getStringExtra("am"));
            sches.add(intent.getStringExtra("sche"));
            Log.d("myDebug_time",intent.getStringExtra("time"));
            Log.d("myDebug_am",intent.getStringExtra("am"));
            Log.d("myDebug_sche",intent.getStringExtra("sche"));
        }
        //Create the contents of the adapter
        List<Map<String,Object>> listitem = new ArrayList<Map<String,Object>>();
        for (int i=0; i < times.size(); i++){
            Map<String,Object> showitem = new HashMap<String,Object>();
            showitem.put("imgVoice",R.drawable.voice);
            showitem.put("time",times.get(i));
            showitem.put("am",ams.get(i));
            showitem.put("sche",sches.get(i));
            showitem.put("imgDel",R.drawable.delete);
            listitem.add(showitem);
        }

        //Create the adapter
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(),listitem,
                R.layout.list_item, new String[]{"imgVoice","time","am","sche","imgDel"},
                new int[]{R.id.imgVoice,R.id.time,R.id.am,R.id.scheTxt,R.id.imgDel} );
//        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(),listitem,
//                R.layout.list_item, new String[]{"imgVoice","imgDel"},
//                new int[]{R.id.imgVoice,R.id.imgDel} );
        ListView listview = (ListView) findViewById(R.id.list_test);
        listview.setAdapter(myAdapter);



        NavigationView navi = (NavigationView)findViewById(R.id.navi_med);
        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId){
                    case R.id.nav_settings:
                        Intent intent_settings = new Intent(ScheduleActivity.this, MonitorActivity.class);
                        startActivity(intent_settings);
                        break;

                    case R.id.nav_map:
                        Intent intent_map = new Intent(ScheduleActivity.this, MapsActivity.class);
                        startActivity(intent_map);
                        break;
                    case R.id.nav_medicine:
//                        Intent intent_med = new Intent(ScheduleActivity.this, ScheduleActivity.class);
//                        startActivity(intent_med);
                        break;
                    case R.id.weather:
                        Intent intent_wea = new Intent(ScheduleActivity.this, WeatherActivity.class);
                        startActivity(intent_wea);
                        break;
                }
                return true;
            }
        });
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
            Toast.makeText(this,"jump to schedule setting",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ScheduleActivity.this, ScheduleSettingActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
    private void initialContents() {
        times.add("8:00");
        ams.add("AM");
        sches.add("schedule1 schedule1");

        times.add("12:30");
        ams.add("PM");
        sches.add("schedule2 schedule2");

        times.add("18:00");
        ams.add("PM");
        sches.add("schedule3 schedule3");
    }
}
