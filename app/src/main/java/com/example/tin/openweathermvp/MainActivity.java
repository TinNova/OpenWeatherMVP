package com.example.tin.openweathermvp;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity implements MainContract.MainScreen {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MainPresenter mainPresenter;

    /*
     * Views within the code
     */
    private TextView tvTodayDate;
    private TextView tvTodayTemp;
    private TextView tvTodayDescription;
    private TextView tvTodayWindSpeed;
    private TextView tvTodayWindDirection;
    private TextView tvLastDataUpdated;
    private TextView tvLocation;
    private ImageView ivTodayIcon;
    private Button btnRefreshData;
    private ConstraintLayout mWeatherUi;
    private ProgressBar mLoadingIndicator;
    private TextView tvNoData;
    private ImageView ivUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialising all of the views */
        btnRefreshData = findViewById(R.id.bt_refresh);
        ivUpdate = findViewById(R.id.iV_updateData);
        mWeatherUi = findViewById(R.id.l_weatherUi);
        mLoadingIndicator = findViewById(R.id.pB_loading_indicator);
        tvNoData = findViewById(R.id.tV_noData);
        tvTodayDate = findViewById(R.id.tV_todayDate);
        tvTodayTemp = findViewById(R.id.tV_todayTemp);
        tvTodayDescription = findViewById(R.id.tV_todayDescription);
        tvTodayWindSpeed = findViewById(R.id.tV_todayWindSpeed);
        tvTodayWindDirection = findViewById(R.id.tV_todayWindDirection);
        tvLocation = findViewById(R.id.tV_lastLocation);
        ivTodayIcon = findViewById(R.id.iV_todayIcon);
        tvLastDataUpdated = findViewById(R.id.tV_lastUpdate);

        try {
            mainPresenter = new MainPresenter(this);
            mainPresenter.getWeatherData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    /* Show Loading Indicator / Hide Weather Data */
    @Override
    public void showLoading() {
        /* Hide the weather data UI */
        mWeatherUi.setVisibility(View.INVISIBLE);
        /* Show the loading indicator */
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void startWeatherService(Intent intent) {
        /* Starts the WeatherIntentService which saves data to SQLite */
        startService(intent);
    }
}

