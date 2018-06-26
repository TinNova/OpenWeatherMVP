package com.example.tin.openweathermvp.models.retrofitNetwork;


import com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tin on 26/06/2018.
 */

public interface ApiMethods {

//    http://api.openweathermap.org/data/2.5/forecast?lat=50.0&lon=50.0&mode=json&units=metric&appid=9f2b5e8d4a6eedad92948909b4690562

    @GET("/data/2.5/forecast")
    Call<WeatherModel> getWeather(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("mode") String mode,
            @Query("units") String units,
            @Query("apiKey") String apiKey
    );
}
