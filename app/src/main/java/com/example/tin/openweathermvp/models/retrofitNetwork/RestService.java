package com.example.tin.openweathermvp.models.retrofitNetwork;

import android.content.Context;

import com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel.WeatherModel;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tin on 28/06/2018.
 */

public class RestService {

    private static ApiMethods INSTANCE;
    private static RestService restService;



    public static RestService getInstance(Context application) {

        /* We only want to create this once
        * This if statement says, if the instance is null, create, else return the existing instance*/
        if (INSTANCE == null) {

            restService = new RestService();

            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.openweathermap.org")
                    .client(provideOkHttp())
                    .build();

            INSTANCE = retrofit.create(ApiMethods.class);

        }

        return restService;
    }

    private static OkHttpClient provideOkHttp() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public Observable<WeatherModel> getWeather(String lat, String lon, String mode, String units, String apiKey) {

        // Here we are inserting .retryWhen() this is to handle an instance of an error being returned
        return INSTANCE.getWeather(lat, lon, mode, units, apiKey);
    }
}
