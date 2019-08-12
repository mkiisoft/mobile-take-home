package com.guestlogix.marianozorrilla.util.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ConnectivityChangeReceiver extends BroadcastReceiver {

    public ConnectivityChangeReceiver() {
        super();
    }

    public void onReceive(Context context, Intent intent) {
        ConnectivityManager.get(context).onReceive(context, intent);
    }

}