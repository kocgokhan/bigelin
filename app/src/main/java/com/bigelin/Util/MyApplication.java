package com.bigelin.Util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.onesignal.OneSignal;


/**
 * Created by GK
 * on 16.02.2019.
 */

public class MyApplication extends Application {

    private static MyApplication _instance;
    private RequestQueue _requestQueue;
    private SharedPreferences _preferences;
    private String ONESIGNAL_APP_ID="4c92fa00-3f60-4e4f-b19a-bfd26ba1a66e";
    private static final String TAG = MyApplication.class
            .getSimpleName();
    public static MyApplication get() {
        return _instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();
        crashlytics.setUserId("myAppUserId");
        FirebaseAnalytics.getInstance(this);

        if (FirebaseCrashlytics.getInstance().didCrashOnPreviousExecution()) {

        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        _instance = this;
        _preferences = PreferenceManager.getDefaultSharedPreferences(this);
        _requestQueue = Volley.newRequestQueue(this);

    }
    public RequestQueue getRequestQueue() {
        return _requestQueue;
    }

    public SharedPreferences getPreferences() {
        return _preferences;
    }

    public SharedPreferences.Editor getPreferencesEditor() {
        return _preferences.edit();
    }
}
