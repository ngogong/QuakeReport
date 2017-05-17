/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL ="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private EarthquakeAsyncTask mEarthquakeAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        /*
        // Create a fake list of earthquake locations.
        ArrayList<String> earthquakes = new ArrayList<>();
        earthquakes.add("San Francisco");
        earthquakes.add("London");
        earthquakes.add("Tokyo");
        earthquakes.add("Mexico City");
        earthquakes.add("Moscow");
        earthquakes.add("Rio de Janeiro");
        earthquakes.add("Paris");

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        */

        /*quiz 18
        ArrayList<EarthquakeInfo> earthquakesList = new ArrayList<EarthquakeInfo>();
        earthquakesList.add(new EarthquakeInfo("7.2","San Francisco", "Feb 2, 2016"));
        earthquakesList.add(new EarthquakeInfo("6.1","London", "July 20, 2015"));
        earthquakesList.add(new EarthquakeInfo("3.9","Tokyo", "Nov 10, 2014"));
        earthquakesList.add(new EarthquakeInfo("5.4","Mexico City", "May 3, 2014"));
        earthquakesList.add(new EarthquakeInfo("2.8","Moscow", "Jan 31, 2013"));
        earthquakesList.add(new EarthquakeInfo("4.9","Rio de Janeiro", "Aug 19, 2012"));
        earthquakesList.add(new EarthquakeInfo("1.6","Paris", "Oct 30, 2011"));
        */

        mEarthquakeAsyncTask = new EarthquakeAsyncTask();
        mEarthquakeAsyncTask.execute(USGS_REQUEST_URL);

    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<EarthquakeInfo>>
    {

        @Override
        protected List<EarthquakeInfo> doInBackground(String... urls) {
            if ((urls.length <1) || (urls[0] == null)){
                return null;
            }
            List<EarthquakeInfo> earthquakesList = QueryUtils.extractEarthquakes(urls[0]);

            return earthquakesList;
        }

        @Override
        protected void onPostExecute(List<EarthquakeInfo> earthquakeInfos) {
            super.onPostExecute(earthquakeInfos);
            updateEarthquakeList(earthquakeInfos);
        }

    }
    private void updateEarthquakeList(List<EarthquakeInfo> earthquakeInfos){
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        final EarthquakeInfoAdapter earthquakeInfoAdapter= new EarthquakeInfoAdapter(this, earthquakeInfos);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                EarthquakeInfo currentEarthquake = earthquakeInfoAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        earthquakeListView.setAdapter(earthquakeInfoAdapter);

    }

}
