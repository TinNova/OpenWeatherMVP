package com.example.tin.openweathermvp.models.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tin.openweathermvp.R;
import com.example.tin.openweathermvp.models.Weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tin on 24/05/2018.
 */

public class WeatherAdapter {

    private List<Weather> mWeather;

    public WeatherAdapter(List<Weather> mWeather) {
        this.mWeather = mWeather;
    }

    // We are passing the weather data via a method, not when the Adapter is created
    public void setWeather(ArrayList<Weather> weather){
        this.mWeather = weather;
        notifyDataSetChanged();

    }

    /*
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        // Create a new View and inflate the list_item Layout into it
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.weather_list_item, viewGroup, false);

        // Return the View we just created
        return new ViewHolder(v);
    }

    /*
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position.
     *
     * @param viewHolder   The ViewHolder which should be updated to represent the
     *                 contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Weather weather = mWeather.get(position);

        viewHolder.tvDate.setText(convertUnixDateToHumanReadable(weather.getUnixDateTime()));
        viewHolder.tvDescription.setText(weather.getWeatherDescription());
        viewHolder.tvTemp.setText(formatTemperature(context, weather.getTempCurrent()));

        Picasso.with(context).load(getSmallArtResourceIdForWeatherCondition(weather.getWeatherId()))
                .into(viewHolder.ivIcon);
    }

    /*
     * Returns the number of items in the listItems List
     */
    @Override
    public int getItemCount() {

        return mWeather.size();
    }

    /*
     * This is the ViewHolder Class, it represents the rows in the RecyclerView (i.e every row is a ViewHolder)
     * In this example each row is made up of an ImageView
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvDate;
        final TextView tvDescription;
        final TextView tvTemp;
        final ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tV_date);
            tvDescription = itemView.findViewById(R.id.tV_description);
            tvTemp = itemView.findViewById(R.id.tV_temp);
            ivIcon = itemView.findViewById(R.id.ic_weather);
        }
    }
}
