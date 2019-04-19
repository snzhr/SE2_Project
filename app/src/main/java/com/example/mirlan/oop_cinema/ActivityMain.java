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
        @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQCODE && resultCode == RESULT_OK) {
            invalidateOptionsMenu();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (User.getUser() instanceof UserAnonymous) {
            menu.getItem(0).setVisible(false);
            menu.getItem(0).setEnabled(false);
            menu.getItem(1).setTitle(R.string.sign_in);
        }
         else {
            menu.getItem(0).setVisible(true);
            menu.getItem(0).setEnabled(true);
            menu.getItem(1).setTitle(R.string.sign_out);
        }
        return super.onCreateOptionsMenu(menu);
    }
     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.signIn) {
            if (User.getUser() instanceof UserRegistered) {
                User.signOut();
                invalidateOptionsMenu();
            }
               else
                startActivityForResult(new Intent(this, ActivitySignIn.class), REQCODE);
            return true;
        }
        else if (item.getItemId() == R.id.profile) {
            startActivity(new Intent(this, ActivityUserProfile.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }