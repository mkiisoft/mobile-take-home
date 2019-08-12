package com.guestlogix.marianozorrilla.util.connectivity;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class ConnectivityManager {

    private static ConnectivityManager instance;

    public interface ConnectionChangedListener {
        void onConnected();

        void onDisconnected();
    }

    private static final long DELAY_TO_CHECK = DateUtils.SECOND_IN_MILLIS;

    private ArrayList<WeakReference<ConnectionChangedListener>> mListeners = new ArrayList<>();

    private Handler handler = new Handler(Looper.getMainLooper());

    private boolean currentlyConnected = false;

    private boolean firstTimeDispatched = false;

    private boolean connectionCheckedOnce = false;

    private Runnable delayedChecker = () -> {
        synchronized (ConnectivityManager.this) {
            onConnectionChange(currentlyConnected);
            firstTimeDispatched = true;
        }
    };

    public static ConnectivityManager get(Context context) {
        if (instance == null) {
            instance = new ConnectivityManager(context);
        }
        return instance;
    }

    public ConnectivityManager(Context context) {
        onReceive(context, null);
    }

    public synchronized void onReceive(Context context, Intent intent) {
        final android.net.ConnectivityManager connectivityManager = (android.net.ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo ni = connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();

        if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
            checkAndFire(true);
        } else if (intent != null && intent.getExtras() != null && intent.getBooleanExtra(android.net.ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
            checkAndFire(false);
        } else if (ni == null) {
            // On Android L, NetworkInfo appears to always be null, have a fallback
            if (hasActiveNetwork(context)) {
                checkAndFire(true);
            } else {
                checkAndFire(false);
            }
        }
    }

    public synchronized void addConnectivityListener(ConnectionChangedListener listener) {
        WeakReference<ConnectionChangedListener> weakReference = new WeakReference<>(listener);
        mListeners.add(weakReference);
        if (firstTimeDispatched) {
            if (isConnected()) {
                listener.onConnected();
            } else {
                listener.onDisconnected();
            }
        }
    }

    public synchronized void removeConnectivityListener(ConnectionChangedListener listener) {
        Iterator<WeakReference<ConnectionChangedListener>> it = mListeners.iterator();
        WeakReference<ConnectionChangedListener> tempConnectionListener;
        while (it.hasNext()) {
            tempConnectionListener = it.next();
            if (tempConnectionListener.get() == null || tempConnectionListener.get() == listener) {
                it.remove();
            }
        }
    }

    public synchronized boolean isConnected() {
        return currentlyConnected;
    }

    private synchronized void checkAndFire(boolean connected) {
        if (connectionCheckedOnce && currentlyConnected == connected) {
            return;
        }

        currentlyConnected = connected;
        handler.removeCallbacks(delayedChecker);
        handler.postDelayed(delayedChecker, DELAY_TO_CHECK);
        connectionCheckedOnce = true;
    }

    private synchronized void onConnectionChange(boolean connected) {
        int pFrom = 0;
        int pTo = 0;
        int numListeners = mListeners.size();
        WeakReference<ConnectionChangedListener> weakReferenceToListener = null;
        ConnectionChangedListener listener = null;
        while (pFrom < numListeners) {
            weakReferenceToListener = mListeners.get(pFrom);
            listener = weakReferenceToListener.get();
            if (listener != null) {
                if (connected) {
                    listener.onConnected();
                } else {
                    listener.onDisconnected();
                }// move to next open destination slot and increment destination
                mListeners.set(pTo++, weakReferenceToListener);
            }
            ++pFrom;
        }
        // there are some elements left in the tail - clean them
        mListeners.subList(pTo, numListeners).clear();
    }

    private boolean hasActiveNetwork(Context context) {
        android.net.ConnectivityManager connectivityManager = (android.net.ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }
}