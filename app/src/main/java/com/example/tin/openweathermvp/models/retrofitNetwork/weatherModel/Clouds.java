
package com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds implements Parcelable {

    @SerializedName("all")
    @Expose
    public Integer all;
    public final static Creator<Clouds> CREATOR = new Creator<Clouds>() {


        public Clouds createFromParcel(Parcel in) {
            return new Clouds(in);
        }

        public Clouds[] newArray(int size) {
            return (new Clouds[size]);
        }

    };

    protected Clouds(Parcel in) {
        this.all = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Clouds() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(all);
    }

    public int describeContents() {
        return 0;
    }

}
