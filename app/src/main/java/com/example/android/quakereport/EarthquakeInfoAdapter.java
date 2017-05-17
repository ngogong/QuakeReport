package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ismile on 5/12/2017.
 */

public class EarthquakeInfoAdapter extends ArrayAdapter<EarthquakeInfo> {

    public EarthquakeInfoAdapter (Activity context, List<EarthquakeInfo> listOfEarthquakeInfo){
        super(context,0,listOfEarthquakeInfo);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                                                                        parent, false);
        }

        EarthquakeInfo curEarthquakeInfo = getItem(position);


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        TextView magTextView = (TextView) listItemView.findViewById(R.id.tv_magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(curEarthquakeInfo.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        magTextView.setText(formatMagnitude(curEarthquakeInfo.getMagnitude()));

        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.offsetlocation);
        String location = curEarthquakeInfo.getLocation();

        final String nearThe = "Near the ";
        String offsetLocation=nearThe;
        String primaryLocation;
        int indexAfterOf;
        int indexStartOf;

        if (location.contains(" of ")== true){
            indexStartOf = location.indexOf(" of");
            primaryLocation = location.substring(indexStartOf+4, location.length());
            offsetLocation=  location.substring(0, indexStartOf+3);
        } else {
            primaryLocation= location;
        }

        offsetTextView.setText(offsetLocation);
        TextView primaryTextView = (TextView) listItemView.findViewById(R.id.primarylocation);
        primaryTextView.setText(primaryLocation);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(curEarthquakeInfo.getTime());

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(formattedDate);
        //dateTextView.setText(curEarthquakeInfo.getTime().toString());

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeTextView.setText(formattedTime);


        return listItemView;
    }
}
