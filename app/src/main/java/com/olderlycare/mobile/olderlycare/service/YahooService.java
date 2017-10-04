package com.olderlycare.mobile.olderlycare.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.olderlycare.mobile.olderlycare.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by aquila on 26/09/2017.
 */

public class YahooService {
    private ServiceCallback callback;
    private String location;
    private Exception error;

    public YahooService(ServiceCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(String locationGot){
        this.location = locationGot;
        new AsyncTask<String, Void, String>(){

            //get responses

            @Override
            protected String doInBackground(String... strings){

                String YQL =  String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")and u= 'c'",strings[0]); //passes location with celsius unit

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL)); //passes query

                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); //get the response text

                    StringBuilder result = new StringBuilder();

                    String line;

                    while ((line = reader.readLine())!=null){
                        result.append(line);
                    }
                    return result.toString();

                }catch (MalformedURLException e){
                    error = e;
                }catch(IOException e){
                    error = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if(s == null && error != null){
                    callback.serviceFailure(error);
                    return;
                }


                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");

                    if (count == 0) {// no such city
                        callback.serviceFailure(new LocationException("No such city found in the database"));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));

                    callback.serviceSuccess(channel);
                }catch (JSONException e){
                    callback.serviceFailure(e);
                }

            }
        }.execute(location);
    }
    public class LocationException extends Exception{
        public LocationException(String message) {
            super(message);
        }
    }
}
