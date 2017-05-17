package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by ismile on 5/12/2017.
 */

public class EarthquakeInfo {
    private double mMagnitude;
    private String mLocation;
    private long  mTimeinMil;
    private String mUrl;

    public EarthquakeInfo(double magnitude, String location, long time, String url) {
        super();
        mMagnitude=magnitude;
        mLocation=location;
        mTimeinMil=time;
        mUrl=url;
    }

    public double getMagnitude (){
        return mMagnitude;
    }

    public String getLocation (){
        return mLocation;
    }

    public long getTime (){
        return mTimeinMil;
    }

    public String getUrl (){
        return mUrl;
    }


}
