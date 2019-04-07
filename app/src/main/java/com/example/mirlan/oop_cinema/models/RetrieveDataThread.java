package com.example.mirlan.oop_cinema.models;

import com.example.mirlan.oop_cinema.helpers.Genre;

import java.util.ArrayList;

/**
 * Created by Mirlan on 26.11.2016.
 */

public class RetrieveDataThread extends Thread {

    private Database db;
    private CallbackInterface callbackInterface;

    public RetrieveDataThread(Database db, CallbackInterface callbackInterface) {
        this.db = db;
        this.callbackInterface = callbackInterface;
    }

    @Override
    public void run() {
        super.run();
        ArrayList<Genre> byGenres = new ArrayList<>();
        ArrayList<String> genreList = db.getDistinctGenres();
        for (int i = 0; i < genreList.size(); i++) {
            byGenres.add(new Genre(genreList.get(i), db.getMoviesByGenre(genreList.get(i))));
        }
        if (!isInterrupted()) {
            callbackInterface.dataRetrieved(byGenres);
        } else
            callbackInterface.canceled();
        callbackInterface = null;
        db = null;
    }
}
