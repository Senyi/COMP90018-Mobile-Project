package com.olderlycare.mobile.olderlycare.data;

import org.json.JSONObject;

/**
 * Created by aquila on 26/09/2017.
 */

public class Item implements JSONpopulator{
    // get both today and forecast
    private Condition condition;
    private Forecast forecast;

    public Condition getCondition() {
        return condition;
    }

    public Forecast getForecast() {
        return forecast;
    }

    @Override

    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
        forecast = new Forecast();
        forecast.populate(data.optJSONArray("forecast"));
    }


}
