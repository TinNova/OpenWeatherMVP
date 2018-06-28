package com.example.tin.openweathermvp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel.WeatherList;
import com.example.tin.openweathermvp.models.adapter.WeatherAdapter;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.example.tin.openweathermvp.models.utils.DateUtils.convertUnixDateToHumanReadable;
import static com.example.tin.openweathermvp.models.utils.WeatherUtils.formatTemperature;
import static com.example.tin.openweathermvp.models.utils.WeatherUtils.formatWindDirection;
import static com.example.tin.openweathermvp.models.utils.WeatherUtils.formatWindSpeed;
import static com.example.tin.openweathermvp.models.utils.WeatherUtils.getLargeArtResourceIdForWeatherCondition;


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

    /*
     * Needed to populate the Adapter and the RecyclerView
     */
    private RecyclerView mRecyclerView;
    private WeatherAdapter mAdapter;

    /* Creating variables for the IntentFilter and ConnectivityBroadcastReceiver*/
    IntentFilter mConnIntentFilter;
    ConnectivityBroadcastReceiver mConnBroadcastReceiver;
    boolean networkConnected;

    /* Used to check if the device is connected to the internet before creating a connection */
    private ConnectivityManager mConnectionManager;
    private NetworkInfo mNetworkInfo;

//    /* Tells main presenter if the noDataScreen is active */
//    boolean noDataScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialising all of the buttons */
        btnRefreshData = findViewById(R.id.bt_refresh);
        ivUpdate = findViewById(R.id.iV_updateData);
        /* Initialising all of the views */
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
//
        /* Setting up the RecyclerView and Adapter*/
        mRecyclerView = findViewById(R.id.rV_weatherList);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new WeatherAdapter(null, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        /* Instantiating the IntentFilter and adding the intent we are looking for via .addAction() */
        mConnIntentFilter = new IntentFilter();
        mConnIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        /* Instantiating the BroadcastReceiver */
        mConnBroadcastReceiver = new ConnectivityBroadcastReceiver();
        /* Used to check if the device is connected to the internet */
        mConnectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        /* Registering the BroadcastReceiver and passing in the Intent Filter */
        registerReceiver(mConnBroadcastReceiver, mConnIntentFilter);

//        noDataScreen = false;

        try {
            mainPresenter = new MainPresenter(this);
            mainPresenter.getWeatherData(MainActivity.this, mConnectionManager);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /* ImageView with onClickListener used to update the weather data */
        ivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                noDataScreen = false;

                try {
                    mainPresenter.getWeatherData(MainActivity.this, mConnectionManager);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

        /* Button used to refresh the weather data */
        btnRefreshData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                noDataScreen = true;

                try {
                    mainPresenter.getWeatherData(MainActivity.this, mConnectionManager);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void startWeatherService(Intent intent) {
        /* Starts the WeatherIntentService which saves data to SQLite */
        /** Intent Service, never runs, therefore data is NOT saved to SQLite */
        startService(intent);
    }

    @Override
    /* Connect to adapter and display 0th weather on the today feature */
    public void showWeather(ArrayList<WeatherList> weatherList) {
        mAdapter.setWeather(weatherList);

        tvTodayDate.setText(convertUnixDateToHumanReadable(weatherList.get(0).getUnixDateTime()));
        tvTodayDescription.setText(weatherList.get(0).getWeather().get(0).getDescription());
        //tvLocation.setText(formatLatLon(this, sharedPrefLatLonArray));
        //tvLastDataUpdated.setText(formatLastUpdateTime(this, sharedPrefLatLonArray[2]));
        tvTodayTemp.setText(formatTemperature(this, weatherList.get(0).getMain().getTemp()));
        tvTodayWindSpeed.setText(formatWindSpeed(this, weatherList.get(0).getWind().getSpeed()));
        tvTodayWindDirection.setText(formatWindDirection(weatherList.get(0).getWind().getDeg()));

        Picasso.with(MainActivity.this)
                .load(getLargeArtResourceIdForWeatherCondition(weatherList.get(0).getWeather().get(0).getId()))
                .into(ivTodayIcon);
    }

    @Override
    public void getContext() {

    }

    @Override
    public void viewOnConnectionChanged(Boolean networkConnActive) {

        Toast.makeText(this, "networkConnActive? " + networkConnActive, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        /* Hide the weather data UI */
        mWeatherUi.setVisibility(View.INVISIBLE);
        /* Show the loading indicator */
        mLoadingIndicator.setVisibility(View.VISIBLE);
        /* Hide the No Data Text */
        tvNoData.setVisibility(View.INVISIBLE);
        /* Hide refresh button */
        btnRefreshData.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideLoading() {
        /* Hide the weather data UI */
        mWeatherUi.setVisibility(View.VISIBLE);
        /* Show the loading indicator */
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        /* Hide the No Data Text */
        tvNoData.setVisibility(View.INVISIBLE);
        /* Hide refresh button */
        btnRefreshData.setVisibility(View.INVISIBLE);
    }


    @Override
    public void showNoNetworkMessage() {
        Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNoDataScreen() {
        /* Hide the weather data UI */
        mWeatherUi.setVisibility(View.INVISIBLE);
        /* Hide the loading indicator */
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        /* Show refresh button */
        btnRefreshData.setVisibility(View.VISIBLE);
        /* Show the No Data Text */
        tvNoData.setVisibility(View.VISIBLE);
    }


    private class ConnectivityBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "IN METHOD, ACTION = " + action);
            if (action != null) {
                mConnectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (mConnectionManager != null)
                    mNetworkInfo = mConnectionManager.getActiveNetworkInfo();
                if (mNetworkInfo != null && mNetworkInfo.isConnectedOrConnecting()) {
                    networkConnected = true;
                    mainPresenter.onConnectionChanged(networkConnected);
                    Log.i(TAG, "Network " + mNetworkInfo.getTypeName() + " connected");
                } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                    networkConnected = false;
                    mainPresenter.onConnectionChanged(networkConnected);
                    Log.d(TAG, "No connectivity!");
                }
            }
        }
    }
}
