package com.example.mirlan.oop_cinema.helpers;

/**
 *
 */

public class Reservation {

    private Movie mMovie;
    private final int mSeatAmt;
    private final String mSeatNums;
    private final long mTimestamp;

    public Reservation(Movie movie, int seatAmt, String seatNums, long timestamp) {
        mMovie = movie;
        mSeatAmt = seatAmt;
        mTimestamp = timestamp;
        mSeatNums = seatNums;
    }

    public Movie getMovie() {
        return mMovie;
    }

    public int getSeatAmt() {
        return mSeatAmt;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public String getSeatNums() {
        return mSeatNums;
    }
}
