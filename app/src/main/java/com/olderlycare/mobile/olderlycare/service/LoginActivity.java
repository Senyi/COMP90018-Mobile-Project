package com.olderlycare.mobile.olderlycare.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.olderlycare.mobile.olderlycare.MapsActivity;
import com.olderlycare.mobile.olderlycare.R;

public class LoginActivity extends AppCompatActivity {

    public String usertype;
    private static final String[] m={"ELDERLY","CARER"};
    private TextView view ;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        view = (TextView) findViewById(R.id.spinnerText);
        spinner = (Spinner) findViewById(R.id.Spinner01);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
        spinner.setVisibility(View.VISIBLE);


        Button loginbtn = (Button) findViewById(R.id.btn_login);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);

                Toast.makeText(LoginActivity.this, "Login Successful",
                        Toast.LENGTH_SHORT).show();
                intent.putExtra("usertype",usertype);
                    startActivity(intent);

            }
        });
    }


    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            view.setText("USERTYPE："+m[arg2]);
            usertype = m[arg2];

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
