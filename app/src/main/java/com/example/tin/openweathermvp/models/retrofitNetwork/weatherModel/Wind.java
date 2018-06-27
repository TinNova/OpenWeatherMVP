
package com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind implements Parcelable {

    @Expose
    public Double speed;
    @Expose
    public Double deg;

    public final static Creator<Wind> CREATOR = new Creator<Wind>() {


        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        public Wind[] newArray(int size) {
            return (new Wind[size]);
        }

    };

    protected Wind(Parcel in) {
        this.speed = ((Double) in.readValue((Double.class.getClassLoader())));
        this.deg = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public Wind() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(speed);
        dest.writeValue(deg);
    }

    public int describeContents() {
        return 0;
    }

    public Double getSpeed() {
        return speed;
    }

    public Double getDeg() {
        return deg;
    }
}
