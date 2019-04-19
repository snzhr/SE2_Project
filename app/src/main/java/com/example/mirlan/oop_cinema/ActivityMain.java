package com.example.mirlan.oop_cinema;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    private static int REQCODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        AppPreferences preferences = new AppPreferences(getApplicationContext());
//        check if app has been launched before
        if (preferences.isRunningFirstTime()) {
            Log.e("OOPAPP", "----> running for the first time <----");
            Database.getInstance(this).saveInitialData(getArray());
//            save in preferences that app has already run
            preferences.savePref(AppPreferences.FIRSTTIME, false);
//            saveToFirebase(getArray());
        }

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainContainer, new FragmentMain())
                    .commit();
    }