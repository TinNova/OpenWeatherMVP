
package com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherModel implements Parcelable {

    @Expose
    public String cod;
    @Expose
    public Double message;
    @Expose
    public Integer cnt;
    /**
     * SerializedName only required if the name you've given the variable is different to the name in the Json
     * If the vairable name is the same as in the Json, then SerializedName is not required
     * */
    @SerializedName("list")
    @Expose
    public ArrayList<WeatherList> list = null;
    @Expose
    public City city;
    public final static Creator<WeatherModel> CREATOR = new Creator<WeatherModel>() {


        public WeatherModel createFromParcel(Parcel in) {
            return new WeatherModel(in);
        }

        public WeatherModel[] newArray(int size) {
            return (new WeatherModel[size]);
        }

    };

    protected WeatherModel(Parcel in) {
        this.cod = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((Double) in.readValue((Double.class.getClassLoader())));
        this.cnt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.list, (WeatherList.class.getClassLoader()));
        this.city = ((City) in.readValue((City.class.getClassLoader())));
    }

    public WeatherModel() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cod);
        dest.writeValue(message);
        dest.writeValue(cnt);
        dest.writeList(list);
        dest.writeValue(city);
    }

    public int describeContents() {
        return 0;
    }

    public ArrayList<WeatherList> getWeatherList() {
        return list;
    }


}
