package com.example.mirlan.oop_cinema.models;

import android.content.Context;

/**
 * Created by Mirlan on 09.12.2016.
 */
public class UserRegistered extends User {

    private String username;
    private double money;
//    private String surname;
//    private String phoneNum;

    UserRegistered(String username, double money) {
        super("registeredUser");
        this.username = username;
        this.money = money;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public double getMoney() {
        return money;
    }

    @Override
    public boolean handlePayment(Context context, double amount) {
        boolean res = false;
        if (amount <= getMoney())
            if (Database.getInstance(context).updateUser(getUsername(), getMoney() - amount)) {
                money -= amount;
                res = true;
            }
        return res;
    }
}
