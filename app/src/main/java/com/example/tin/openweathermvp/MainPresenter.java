package com.example.tin.openweathermvp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tin.openweathermvp.models.NetworkConnection;
import com.example.tin.openweathermvp.models.NetworkListener;
import com.example.tin.openweathermvp.models.NetworkUtils;
import com.example.tin.openweathermvp.models.Weather;
import com.example.tin.openweathermvp.models.WeatherIntentService;
import com.example.tin.openweathermvp.models.utils.IntentServiceUtils;

import java.net.MalformedURLException;
import java.util.ArrayList;


public class MainPresenter implements MainContract.MainPresenter {
    private MainContract.MainScreen mainScreen;

    private static final String TAG = MainPresenter.class.getSimpleName();

    /* Strings for the SQL Intent Service */
    public static final String SQL_WEATHER_DATA = "sql_weather_data";

    private Boolean mNetworkConnActive;

    private Context mContext;

    // Used to check if the device has internet connection
    private ConnectivityManager connectionManager;
    private NetworkInfo networkInfo;


    MainPresenter(MainContract.MainScreen screen) throws MalformedURLException {
        this.mainScreen = screen;
    }


    @Override
    public void getWeatherData(Context context, ConnectivityManager connectivityManager) throws MalformedURLException {
         /* Checking If The Device Is Connected To The Internet */
        if (connectivityManager != null)
            networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            mContext = context;
            String url = NetworkUtils.getUrl(50.00, 50.00);
            mainScreen.showLoading();


        /*
         * Use the String URL "weatherRequestUrl" to request the JSON from the server
         * and parse it
         */
            NetworkConnection.getInstance(context).getResponseFromHttpUrl(url, new NetworkListener() {
                @Override
                public void getWeatherArrayList(ArrayList<Weather> weather) {

                /* Save Weather ContentValues to Bundle */
                    Bundle weatherDataBundle = IntentServiceUtils.saveWeatherDataToSql(weather);
                    /* Send Bundle to the SqlIntentService to be saved in SQLite */
                    Intent saveSqlIntent = new Intent((Context) mainScreen, WeatherIntentService.class);

                    saveSqlIntent.putExtras(weatherDataBundle);

                /* Service is started from the View */
                    mainScreen.startWeatherService(saveSqlIntent);

                /* Show weather on screen */
                    mainScreen.showWeather(weather);
                    mainScreen.hideLoading();

                    //TODO:
                /* This displays data delivered from JSON */
                /* Now we need to populate the Adapter, & the Text/ImageViews*/
                    //populateTodaysDate(weather);

                }
                // Here insert:
                // 1. mainScreen.showLoading(); // Launches showLoading Method in MainScreen/MainActivity
                // 2. url = NetworkUtils.getUrl(); // Now url wil contain the url constructed from a method in the NetworkUtils Class
                // 3. mWeather = getWeatherArrayList(url); // Passes url to getWeatherArrayList method which uses Volley to request response, then it'll be parsed, and passed here
                // 4. mainScreen.showWeather(mWeather); // This sends the mWeather ArrayList to MainActivity where it'll be added to the adapter
                // 5. mainScreen.hideLoading();
            });
        } else {

            //mainScreen.showLoading();

            /** IF mNETWORKCONNACTIVE == FALSE, LOAD DATA FROM SQL**/
        }
            /** ELSE IF SQL DATA == 0 or NULL SHOW NO DATA SCREEN **/

    }

    @Override
    public void onConnectionChanged(Boolean networkConnActive) {
        mNetworkConnActive = networkConnActive;

        mainScreen.viewOnConnectionChanged(mNetworkConnActive);

        Log.d(TAG, "networkConnActive? " + mNetworkConnActive);

    }
}
