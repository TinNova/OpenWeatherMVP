
package com.example.tin.openweathermvp.models.retrofitNetwork.weatherModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main implements Parcelable {

    @Expose
    public Double temp;
    @Expose
    public Double tempMin;
    @Expose
    public Double tempMax;
    @Expose
    public Double pressure;
    @SerializedName("sea_level")
    @Expose
    public Double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    public Double grndLevel;
    @Expose
    public Double humidity;
    @SerializedName("temp_kf")
    @Expose
    public Double tempKf;
    public final static Creator<Main> CREATOR = new Creator<Main>() {


        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        public Main[] newArray(int size) {
            return (new Main[size]);
        }

    };

    protected Main(Parcel in) {
        this.temp = ((Double) in.readValue((Double.class.getClassLoader())));
        this.tempMin = ((Double) in.readValue((Double.class.getClassLoader())));
        this.tempMax = ((Double) in.readValue((Double.class.getClassLoader())));
        this.pressure = ((Double) in.readValue((Double.class.getClassLoader())));
        this.seaLevel = ((Double) in.readValue((Double.class.getClassLoader())));
        this.grndLevel = ((Double) in.readValue((Double.class.getClassLoader())));
        this.humidity = ((Double) in.readValue((Integer.class.getClassLoader())));
        this.tempKf = ((Double) in.readValue((Integer.class.getClassLoader())));
    }

    public Main() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(temp);
        dest.writeValue(tempMin);
        dest.writeValue(tempMax);
        dest.writeValue(pressure);
        dest.writeValue(seaLevel);
        dest.writeValue(grndLevel);
        dest.writeValue(humidity);
        dest.writeValue(tempKf);
    }

    public int describeContents() {
        return 0;
    }

    public Double getTemp() {
        return temp;
    }
}
