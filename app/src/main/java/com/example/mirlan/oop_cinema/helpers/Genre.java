package com.example.mirlan.oop_cinema.helpers;

import java.util.ArrayList;

/**
 *
 */

public class Genre {

    private String name;
    private ArrayList<Movie> movieList;

    public Genre(String name, ArrayList<Movie> list) {
        this.name = name;
        movieList = list;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }
}
