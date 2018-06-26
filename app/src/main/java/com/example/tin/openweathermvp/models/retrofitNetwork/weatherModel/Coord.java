
package com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord implements Parcelable {

    @SerializedName("lat")
    @Expose
    public Integer lat;
    @SerializedName("lon")
    @Expose
    public Integer lon;
    public final static Creator<Coord> CREATOR = new Creator<Coord>() {


        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        public Coord[] newArray(int size) {
            return (new Coord[size]);
        }

    };

    protected Coord(Parcel in) {
        this.lat = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.lon = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Coord() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lat);
        dest.writeValue(lon);
    }

    public int describeContents() {
        return 0;
    }

}
