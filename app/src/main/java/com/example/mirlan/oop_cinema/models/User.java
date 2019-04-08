package com.example.mirlan.oop_cinema.models;

import android.content.Context;

/**
 * Created by Mirlan on 09.12.2016.
 */

public abstract class User {

    private static User mUser;
    private String state;

    public static User getUser() {
        if (mUser == null)
            mUser = new UserAnonymous();
        return mUser;
    }

    public static User getUser(String email, double money) {
        if(mUser != null && mUser instanceof UserAnonymous)
            mUser = new UserRegistered(email, money);
        return mUser;
    }

    public static void signOut() {
        mUser = new UserAnonymous();
    }

    User(String state) {
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public abstract String getUsername();

    public abstract double getMoney();

    public abstract boolean handlePayment(Context context, double amount);
}
