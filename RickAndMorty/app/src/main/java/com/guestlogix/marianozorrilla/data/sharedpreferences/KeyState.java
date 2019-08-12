package com.guestlogix.marianozorrilla.data.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.guestlogix.marianozorrilla.util.Constants;

public class KeyState implements Constants {

    public static boolean episodesDone(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(EPISODE_DONE, false);
    }

    public static void setEpisodesDone(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(EPISODE_DONE, true).apply();
    }

    public static boolean charactersDone(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(EPISODE_DONE, false);
    }

    public static void setCharactersDone(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(EPISODE_DONE, true).apply();
    }
}
