package com.example.mirlan.oop_cinema.models;

import android.content.Context;

/**
 * Created by Mirlan on 09.12.2016.
 */

public class UserAnonymous extends User {

    UserAnonymous() {
        super("anonymous");
    }

    public String getUsername() {
        return null;
    }

    @Override
    public double getMoney() {
        return 0;
    }

    @Override
    public boolean handlePayment(Context context, double amount) {
        return false;
    }
}
