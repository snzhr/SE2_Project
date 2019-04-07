package com.example.mirlan.oop_cinema.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */

public class AppPreferences {

    public final static String FIRSTTIME = "isFirstTime?";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AppPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void savePref(String key, Object value) {
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }
        editor.commit();
    }

    public boolean isRunningFirstTime() {
        return sharedPreferences.getBoolean(FIRSTTIME, true);
    }
}

