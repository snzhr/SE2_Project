package com.ldt.cinematicket.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class DateShowTime {
    @Override
    public String toString() {
        return "DateShowTime{" +
                "mId=" + mId +
                ", mDate='" + mDate + '\'' +
                ", mDetailShowTimes=" + mDetailShowTimes +
                '}';
    }

    @SerializedName("id")
    private int mId;
    @SerializedName("date")
    private String mDate;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public ArrayList<DetailShowTime> getDetailShowTimes() {
        return mDetailShowTimes;
    }

    public void setDetailShowTimes(ArrayList<DetailShowTime> mDetailShowTimes) {
        this.mDetailShowTimes = mDetailShowTimes;
    }

    @SerializedName("detailShowTimes")
    private ArrayList<DetailShowTime> mDetailShowTimes;
}
