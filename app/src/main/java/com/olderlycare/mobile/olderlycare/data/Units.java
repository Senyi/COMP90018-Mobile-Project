package com.olderlycare.mobile.olderlycare.data;

import org.json.JSONObject;

/**
 * Created by aquila on 26/09/2017.
 */

public class Units implements JSONpopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}
