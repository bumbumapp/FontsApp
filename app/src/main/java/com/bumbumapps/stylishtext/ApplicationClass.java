package com.bumbumapps.stylishtext;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.crashlytics.FirebaseCrashlytics;


public class ApplicationClass extends Application {
    private static final String ONESIGNAL_APP_ID = "########-####-####-####-############";
    private static final String NIGHT_MODE = "NightMode";
    private static final String PREF = "AppSettingsPrefs";
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable verbose OneSignal logging to debug issues if needed.
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        //Night Mode Check
        final SharedPreferences appSettingsPrefs = getSharedPreferences(PREF,0);
        final boolean isNightModeOn = appSettingsPrefs.getBoolean(NIGHT_MODE, false);
        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }

    }
}
