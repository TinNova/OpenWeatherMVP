package com.example.tin.openweathermvp.models.retrofitNetwork;

/**
 * Created by Tin on 28/06/2018.
 */

public class WeatherQueryParams {

    String lat;
    String lon;
    String mode;
    String units;
    String apiKey;

    public WeatherQueryParams(String lat, String lon, String mode, String units, String apiKey) {
        this.lat = lat;
        this.lon = lon;
        this.mode = mode;
        this.units = units;
        this.apiKey = apiKey;
    }
}
