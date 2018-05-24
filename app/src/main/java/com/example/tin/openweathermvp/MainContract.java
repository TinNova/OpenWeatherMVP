package com.example.tin.openweathermvp;

import android.content.Intent;

import com.example.tin.openweathermvp.models.Weather;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Tin on 22/05/2018.
 */

public interface MainContract {

    interface MainScreen {

        void showLoading();

        void startWeatherService(Intent intent);

        void showWeather(ArrayList<Weather> weather);
    }

    interface MainPresenter {

        void getWeatherData() throws MalformedURLException;

    }
}
