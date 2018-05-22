package com.example.tin.openweathermvp.models;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Tin on 22/05/2018.
 */

public class WeatherIntentService extends IntentService {

    private static final String TAG = WeatherIntentService.class.getSimpleName();

    private ArrayList<Weather> mWeather;

    public WeatherIntentService() {
        super("WeatherIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(TAG, "IntentService Weather Data: " + mWeather);

        if (intent != null) {

            mWeather = intent.getParcelableArrayListExtra(MainActivity.SQL_WEATHER_DATA);

            ContentValues[] weatherValues = IntentServiceUtils.parseWeatherArrayToCv(mWeather);

            /*
             * If statement is used in case a null weatherValue is returned, this prevents a
             * NullPointerException and it prevent us saving null data to the SQL database
             */
            if (weatherValues != null && weatherValues.length != 0) {

                /* Get a handle on the ContentResolver to delete and insert data */
                ContentResolver contentResolver = getApplicationContext().getContentResolver();

                /* Delete old weather data because we don't need to keep multiple days' data */
                contentResolver.delete(
                        WeatherContract.WeatherEntry.CONTENT_URI,
                        null,
                        null);

                /* Insert our new weather data into Sunshine's ContentProvider */
                contentResolver.bulkInsert(
                        WeatherContract.WeatherEntry.CONTENT_URI,
                        weatherValues);

                Log.d(TAG, "The Data Added: " + weatherValues);

                Log.d(TAG, "IntentService Weather Data: " + mWeather);
            }
        }
    }
}
