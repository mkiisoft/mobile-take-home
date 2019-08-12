package com.guestlogix.marianozorrilla.application;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.guestlogix.marianozorrilla.util.connectivity.ConnectivityChangeReceiver;

public class GuestLogixApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(new ConnectivityChangeReceiver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
}
