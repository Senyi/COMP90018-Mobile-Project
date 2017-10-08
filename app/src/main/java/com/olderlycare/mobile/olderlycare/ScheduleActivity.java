package com.olderlycare.mobile.olderlycare;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.olderlycare.mobile.olderlycare.service.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private ArrayList<String> times = new ArrayList<String>();
    private ArrayList<String> ams = new ArrayList<String>();;
    private ArrayList<String> sches = new ArrayList<String>();;
    DBHelper myDb;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        myDb = new DBHelper(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_schedule);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialContents();

        //Create the contents of the adapter
        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < times.size(); i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("imgVoice", R.drawable.medicine);
            showitem.put("time", times.get(i));
            showitem.put("am", ams.get(i));
            showitem.put("sche", sches.get(i));
            showitem.put("imgDel", R.drawable.delete);
            listitem.add(showitem);
        }

        //Create the adapter
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listitem,
                R.layout.list_item, new String[]{"imgVoice", "time", "am", "sche", "imgDel"},
                new int[]{R.id.imgVoice, R.id.time, R.id.am, R.id.scheTxt, R.id.imgDel});


        ListView listview = (ListView) findViewById(R.id.list_test);
        listview.setAdapter(myAdapter);

        //The navigation drawer
        NavigationView navi = (NavigationView) findViewById(R.id.navi_med);
        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.nav_carer:
                        Intent intent_carer = new Intent(ScheduleActivity.this,
                                MonitorActivity.class);
                        startActivity(intent_carer);
                        break;

                    case R.id.nav_map:
                        Intent intent_map = new Intent(ScheduleActivity.this, MapsActivity.class);
                        startActivity(intent_map);
                        break;
                    case R.id.nav_medicine:
                        break;
                    case R.id.weather:
                        Intent intent_wea = new Intent(ScheduleActivity.this,
                                WeatherActivity.class);
                        startActivity(intent_wea);
                        break;
                    case R.id.logout:
                        Intent intent_logout = new Intent(ScheduleActivity.this,
                                LoginActivity.class);
                        Toast.makeText(ScheduleActivity.this, "Logout Successful",
                                Toast.LENGTH_SHORT).show();
                        startActivity(intent_logout);
                        break;
                }
                return true;
            }
        });

        //The button for creating new medicine Alarm
        Button medicinebtn = (Button) findViewById(R.id.button_medicine);

        medicinebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this, ScheduleSettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(mToggle.onOptionsItemSelected(item)) {
        return true;
    }
    return super.onOptionsItemSelected(item);
}


    private void initialContents() {

        Cursor res = myDb.getAllData();
        while (res.moveToNext()){
            int id = res.getInt(0);
            times.add(res.getString(1));
            ams.add(res.getString(2));
            sches.add(res.getString(3));
        }

    }

}
