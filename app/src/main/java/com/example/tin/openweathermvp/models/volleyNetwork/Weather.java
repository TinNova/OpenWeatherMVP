package com.example.tin.openweathermvp.models.volleyNetwork;

import android.os.Parcel;
import android.os.Parcelable;


public class Weather implements Parcelable {

    private final long unixDateTime;
    private final String calculateDateTime;
    private final double tempCurrent;
    private final double tempMin;
    private final double tempMax;
    private final String weatherTitle;
    private final String weatherDescription;
    private final int weatherId;
    private final double windSpeed;
    private final double windDegree;

    public Weather(long unixDateTime, String calculateDateTime, double tempCurrent, double tempMin,
                   double tempMax, String weatherTitle, String weatherDescription, int weatherId,
                   double windSpeed, double windDegree) {
        this.unixDateTime = unixDateTime;
        this.calculateDateTime = calculateDateTime;
        this.tempCurrent = tempCurrent;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.weatherTitle = weatherTitle;
        this.weatherDescription = weatherDescription;
        this.weatherId = weatherId;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
    }

    private Weather(Parcel in) {
        unixDateTime = in.readLong();
        calculateDateTime = in.readString();
        tempCurrent = in.readDouble();
        tempMin = in.readDouble();
        tempMax = in.readDouble();
        weatherTitle = in.readString();
        weatherDescription = in.readString();
        weatherId = in.readInt();
        windSpeed = in.readDouble();
        windDegree = in.readDouble();
    }

    public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(unixDateTime);
        parcel.writeString(calculateDateTime);
        parcel.writeDouble(tempCurrent);
        parcel.writeDouble(tempMin);
        parcel.writeDouble(tempMax);
        parcel.writeString(weatherTitle);
        parcel.writeString(weatherDescription);
        parcel.writeInt(weatherId);
        parcel.writeDouble(windSpeed);
        parcel.writeDouble(windDegree);
    }

    public long getUnixDateTime() {

        return unixDateTime;
    }

    public String getCalculateDateTime() {

        return calculateDateTime;
    }

    public double getTempCurrent() {

        return tempCurrent;
    }

    public double getTempMin() {

        return tempMin;
    }

    public double getTempMax() {

        return tempMax;
    }

    public String getWeatherTitle() {

        return weatherTitle;
    }

    public String getWeatherDescription() {

        return weatherDescription;
    }

    public int getWeatherId() {

        return weatherId;
    }

    public double getWindSpeed() {

        return windSpeed;
    }

    public double getWindDegree() {

        return windDegree;
    }
}
