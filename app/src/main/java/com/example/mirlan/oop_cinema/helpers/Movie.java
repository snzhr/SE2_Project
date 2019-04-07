package com.example.mirlan.oop_cinema.helpers;

import java.io.Serializable;

/**
 *
 */

public class Movie implements Serializable {

    private int id;
    private String name;
    private String poster;
    private String genre;
    private String description;
    private String slogan;
    private int cost;


    public Movie(String name, String moviePoster, String genre) {
        this.name = name;
        this.poster = moviePoster;
        this.genre = genre;
    }

    public Movie(String name, String moviePoster, String genre, String slogan, String description, int cost) {
        this.name = name;
        this.poster = moviePoster;
        this.genre = genre;
        this.slogan = slogan;
        this.description = description;
        this.cost = cost;
    }

    public Movie(int id, String name, String moviePoster, String genre, String slogan, String description, int cost) {
        this.id = id;
        this.name = name;
        this.poster = moviePoster;
        this.genre = genre;
        this.slogan = slogan;
        this.description = description;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getPoster() {
        return poster;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
}
