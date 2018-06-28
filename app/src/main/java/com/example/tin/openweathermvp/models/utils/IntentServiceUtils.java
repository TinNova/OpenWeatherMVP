package com.example.tin.openweathermvp.models.utils;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;

import com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel.WeatherList;
import com.example.tin.openweathermvp.models.sql.WeatherContract;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.tin.openweathermvp.MainPresenter.SQL_WEATHER_DATA;


public class IntentServiceUtils {

    private static final String TAG = IntentServiceUtils.class.getSimpleName();

    /* Method which launches the WeatherIntentService */
    public static Bundle saveWeatherDataToSql(ArrayList<WeatherList> weatherList) {

        Bundle sqlIntentBundle = new Bundle();

        if (weatherList != null) {
            sqlIntentBundle.putParcelableArrayList(SQL_WEATHER_DATA, weatherList);
        }

        return sqlIntentBundle;
    }

    /* Parsing the weather array into ContentValues in order to save them into the SQL database */
    public static ContentValues[] parseWeatherArrayToCv(ArrayList<WeatherList> weatherList) {

        /* ContentValues to save data to SQL */
        ContentValues[] weatherContentValues = new ContentValues[weatherList.size()];

        /* Using a for loop to cycle through each JsonObject within the listJsonArray */
        for (int i = 0; i < weatherList.size(); i++) {

            long unixDateTime = weatherList.get(i).getUnixDateTime();
            String calculateDateTime = weatherList.get(i).getCalculatedDateTime();
            double tempCurrent = weatherList.get(i).getMain().getTemp();
            double tempMin = weatherList.get(i).getMain().getTempMin();
            double tempMax = weatherList.get(i).getMain().getTempMax();
            String weatherDescription = weatherList.get(i).getWeather().get(0).getDescription();
            int weatherIcon = weatherList.get(i).getWeather().get(0).getId();
            double windSpeed = weatherList.get(i).getWind().getSpeed();
            double windDegree = weatherList.get(i).getWind().getDeg();

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
