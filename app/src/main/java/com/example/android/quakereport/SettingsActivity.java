package com.example.android.quakereport;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by ismile on 5/17/2017.
 */

public class SettingsActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.settings_activity);
        }
    public static class EarthquakePreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        private static final String LOG_TAG = EarthquakePreferenceFragment.class.getName();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- oncreate 1 " );
            addPreferencesFromResource(R.xml.settings_main);
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- oncreate 2 " );

            Preference minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key));
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- oncreate 3 " );

            bindPreferenceSummaryToValue(minMagnitude);
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- oncreate 4 " );

        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- onpreferencechange  1 " );
            preference.setSummary(stringValue);
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- onpreferencechange  2 " );
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- bindpreferencesummarytovalue  1 " );
            preference.setOnPreferenceChangeListener(this);
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- bindpreferencesummarytovalue  2 " );
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- bindpreferencesummarytovalue  3 " );
            String preferenceString = preferences.getString(preference.getKey(), "");
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- bindpreferencesummarytovalue  4 " );
            onPreferenceChange(preference, preferenceString);
            Log.e(LOG_TAG, " --earthquakepreferencefragment-- bindpreferencesummarytovalue  5 " );
        }
    }
}

