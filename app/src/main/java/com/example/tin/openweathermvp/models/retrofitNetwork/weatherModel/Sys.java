
package com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys implements Parcelable {

    @SerializedName("pod")
    @Expose
    public String pod;
    public final static Creator<Sys> CREATOR = new Creator<Sys>() {


        public Sys createFromParcel(Parcel in) {
            return new Sys(in);
        }

        public Sys[] newArray(int size) {
            return (new Sys[size]);
        }

    };

    protected Sys(Parcel in) {
        this.pod = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Sys() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pod);
    }

    public int describeContents() {
        return 0;
    }

}
