package com.olderlycare.mobile.olderlycare.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aquila on 28/09/2017.
 */

public class Forecast implements JSONArrayPopulator {
    private int code[] = new int[10];
    private int temperatureHigh[] = new int[10];
    private int temperatureLow[] = new int[10];
    private String description[] = new String[10];
    private String day[] = new String[10];

    public int getCode(int i) {
        return code[i];
    }

    public int getTemperatureHigh(int i) {
        return temperatureHigh[i];
    }

    public int getTemperatureLow(int i) {
        return temperatureLow[i];
    }

    public String getDescription(int i) {
        return description[i];
    }

    public String getDay(int i) {
        return day[i];
    }

    @Override
    public void populate(JSONArray data) {
        try {
            for(int i=0;i<data.length();i++){

                code[i] = data.getJSONObject(i).optInt("code");
                temperatureHigh[i] = data.getJSONObject(i).optInt("high");
                temperatureLow[i] = data.getJSONObject(i).optInt("low");
                description[i] = data.getJSONObject(i).optString("text");
                day[i] = data.getJSONObject(i).optString("day");
            }
        }catch (JSONException e){

        }

    }
}
