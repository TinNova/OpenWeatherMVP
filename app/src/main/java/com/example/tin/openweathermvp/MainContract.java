package com.example.tin.openweathermvp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.tin.openweathermvp.models.Weather;

import java.net.MalformedURLException;
import java.util.ArrayList;


public interface MainContract {

    interface MainScreen {

        void showLoading();

        void hideLoading();

        void startWeatherService(Intent intent);

        void showWeather(ArrayList<Weather> weather);

        void getContext();

        void viewOnConnectionChanged(Boolean networkConnActive);

    }

    interface MainPresenter {

        void getWeatherData(Context context, ConnectivityManager connectivityManager) throws MalformedURLException;

        void onConnectionChanged(Boolean networkConnActive);

    }

}
