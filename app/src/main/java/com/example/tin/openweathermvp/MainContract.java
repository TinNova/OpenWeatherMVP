package com.example.tin.openweathermvp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel.WeatherList;
import com.example.tin.openweathermvp.models.volleyNetwork.Weather;

import java.net.MalformedURLException;
import java.util.ArrayList;


public interface MainContract {

    interface MainScreen {

        void showLoading();

        void hideLoading();

        void startWeatherService(Intent intent);

        void showWeather(ArrayList<WeatherList> weatherLists);

        void getContext();

        /* Whenever BroadcastReceiver detects a network change the user is notified via a Toast*/
        void viewOnConnectionChanged(Boolean networkConnActive);

        void showNoNetworkMessage();

        void showNoDataScreen();
    }

    interface MainPresenter {

        void getWeatherData(Context context, ConnectivityManager connectivityManager) throws MalformedURLException;

        void onConnectionChanged(Boolean networkConnActive);
    }
}
