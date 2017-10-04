package com.olderlycare.mobile.olderlycare.data;


import org.json.JSONObject;

/**
 * Created by aquila on 26/09/2017.
 */

public class Channel implements JSONpopulator{
    private Item item;
    private Units units;

    public Item getItem() {
        return item;
    }

    public Units getUnits() {
        return units;
    }

    @Override
    public void populate(JSONObject data) {
        units = new Units();
        units.populate(data.optJSONObject("units")); //inject unit json objects

        item = new Item();
        item.populate(data.optJSONObject("item"));  //inject item json objects
    }
}
