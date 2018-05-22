package com.example.tin.openweathermvp.models;

import android.net.Uri;
import android.util.Log;

import com.example.tin.openweathermvp.BuildConfig;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tin on 22/05/2018.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    /*
     * URL STRUCTURE
     * http://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}&appid={API_KEY}
     *
     * EXAMPLE URL
     * http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&appid=1010101010
     *
     * URL STRUCTURE WITH ADDITIONAL PARAMETERS
     * http://api.openweathermap.org/data/2.5/forecast?lat={lat}&lon={lon}?units={UNIT_TYPE}&appid={API_KEY}
     */

    /* Base url for the URL */
    private static final String BASE_WEATHER_URL = "http://api.openweathermap.org/data/2.5/forecast";

    /* Values we want for the format: json/xml, units: imperial/metric and the api key */
    private static final String format = "json";
    private static final String units = "metric";
    private static final String API_KEY = BuildConfig.OPEN_WEATHER_MAP_API_KEY;

    /* Parameters that allow us to specify the feed type, unit type and api key */
    private static final String FORMAT_PARAM = "mode";
    private static final String UNITS_PARAM = "units";
    private static final String API_KEY_PARAM = "appid";

    /* Parameters for the Latitude and Longitude */
    private static final String LAT_PARAM = "lat";
    private static final String LON_PARAM = "lon";

    public static String getUrl(double currentLatitude, double currentLongitude) {

        return buildUrl(currentLatitude, currentLongitude);
    }


    /**
     * Builds the URL with our specified parameters
     *
     * @param latitude  double representing the users current latitude
     * @param longitude double representing the users current longitude
     * @return The URL that will be used to query the OpenWeatherMap server
     */
    private static String buildUrl(double latitude, double longitude) {
        Uri openWeatherQueryUri = Uri.parse(BASE_WEATHER_URL).buildUpon()
                .appendQueryParameter(LAT_PARAM, String.valueOf(latitude))
                .appendQueryParameter(LON_PARAM, String.valueOf(longitude))
                .appendQueryParameter(FORMAT_PARAM, format)
                .appendQueryParameter(UNITS_PARAM, units)
                .appendQueryParameter(API_KEY_PARAM, API_KEY)
                .build();

        try {

            URL weatherQueryUrl = new URL(openWeatherQueryUri.toString());
            Log.v(TAG, "weatherQueryUrl: " + weatherQueryUrl);
            convertUrlToString(weatherQueryUrl);
            return convertUrlToString(weatherQueryUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String convertUrlToString(URL url) throws MalformedURLException {

        return url.toString();
    }
}
