package com.example.mirlan.oop_cinema.models;

import com.example.mirlan.oop_cinema.helpers.Genre;

import java.util.ArrayList;

/**
 * Created by Mirlan on 26.11.2016.
 */

public interface CallbackInterface {

    void dataRetrieved(ArrayList<Genre> movies);

    void canceled();
}
