
package com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City implements Parcelable {

    @Expose
    public Integer id;
    @Expose
    public String name;
    @Expose
    public Coord coord;
    @Expose
    public String country;

    public final static Creator<City> CREATOR = new Creator<City>() {


        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        public City[] newArray(int size) {
            return (new City[size]);
        }

    };

    protected City(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.coord = ((Coord) in.readValue((Coord.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
    }

    public City() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(coord);
        dest.writeValue(country);
    }

    public int describeContents() {
        return 0;
    }

}
