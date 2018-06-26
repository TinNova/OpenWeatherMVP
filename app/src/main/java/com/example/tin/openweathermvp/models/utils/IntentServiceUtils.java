package com.example.tin.openweathermvp.models.utils;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;

import com.example.tin.openweathermvp.models.volleyNetwork.Weather;
import com.example.tin.openweathermvp.models.sql.WeatherContract;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.tin.openweathermvp.MainPresenter.SQL_WEATHER_DATA;


public class IntentServiceUtils {

    private static final String TAG = IntentServiceUtils.class.getSimpleName();

    /* Method which launches the WeatherIntentService */
    public static Bundle saveWeatherDataToSql(ArrayList<Weather> weather) {

        Bundle sqlIntentBundle = new Bundle();

        if (weather != null) {
            sqlIntentBundle.putParcelableArrayList(SQL_WEATHER_DATA, weather);
        }

        return sqlIntentBundle;
    }

    /* Parsing the weather array into ContentValues in order to save them into the SQL database */
    public static ContentValues[] parseWeatherArrayToCv(ArrayList<Weather> weather) {

        /* ContentValues to save data to SQL */
        ContentValues[] weatherContentValues = new ContentValues[weather.size()];

        /* Using a for loop to cycle through each JsonObject within the listJsonArray */
        for (int i = 0; i < weather.size(); i++) {

            long unixDateTime = weather.get(i).getUnixDateTime();
            String calculateDateTime = weather.get(i).getCalculateDateTime();
            double tempCurrent = weather.get(i).getTempCurrent();
            double tempMin = weather.get(i).getTempMin();
            double tempMax = weather.get(i).getTempMax();
            String weatherDescription = weather.get(i).getWeatherDescription();
            int weatherIcon = weather.get(i).getWeatherId();
            double windSpeed = weather.get(i).getWindSpeed();
            double windDegree = weather.get(i).getWindDegree();

            /* Preparing data for SQLite */
            ContentValues weatherValues = new ContentValues();
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_UNIX_DATE, unixDateTime);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_CALC_DATE, calculateDateTime);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_TEMP_CURRENT, tempCurrent);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_TEMP_MIN, tempMin);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_TEMP_MAX, tempMax);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_DESC, weatherDescription);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, weatherIcon);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, windSpeed);
            weatherValues.put(WeatherContract.WeatherEntry.COLUMN_WIND_DEGREE, windDegree);

            weatherContentValues[i] = weatherValues;
        }

        Log.d(TAG, "weatherContentValues" + Arrays.toString(weatherContentValues));

        return weatherContentValues;
    }
}
