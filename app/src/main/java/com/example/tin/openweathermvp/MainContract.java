package com.example.tin.openweathermvp;

import android.content.Intent;

import java.net.MalformedURLException;

/**
 * Created by Tin on 22/05/2018.
 */

public interface MainContract {

    interface MainScreen {

        void showLoading();

        void startWeatherService(Intent intent);
    }

    interface MainPresenter {

        void getWeatherData() throws MalformedURLException;

    }
}
