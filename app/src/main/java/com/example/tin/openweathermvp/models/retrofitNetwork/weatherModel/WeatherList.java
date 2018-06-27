
package com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherList implements Parcelable {

    @SerializedName("dt")
    @Expose
    public Long unixDateTime;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("weather")
    @Expose
    public ArrayList<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("dt_txt")
    @Expose
    public String dtTxt;
    public final static Creator<WeatherList> CREATOR = new Creator<WeatherList>() {


        public WeatherList createFromParcel(Parcel in) {
            return new WeatherList(in);
        }

        public WeatherList[] newArray(int size) {
            return (new WeatherList[size]);
        }

    };

    public WeatherList(Parcel in) {
        this.unixDateTime = ((Long) in.readValue((Integer.class.getClassLoader())));
        this.main = ((Main) in.readValue((Main.class.getClassLoader())));
        in.readList(this.weather, (Weather.class.getClassLoader()));
        this.clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
        this.wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
        this.sys = ((Sys) in.readValue((Sys.class.getClassLoader())));
        this.dtTxt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public WeatherList() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(unixDateTime);
        dest.writeValue(main);
        dest.writeList(weather);
        dest.writeValue(clouds);
        dest.writeValue(wind);
        dest.writeValue(sys);
        dest.writeValue(dtTxt);
    }

    public int describeContents() {
        return 0;
    }

    public Main getMain() {
        return main;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public Sys getSys() {
        return sys;
    }

    public Long getUnixDateTime() {
        return unixDateTime;
    }
}
