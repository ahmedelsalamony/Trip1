package com.example.itimobiletrack.trip1.home.upcomingtrips;

/**
 * Created by ahmed on 2/14/2017.
 */

public class upcomingdata {

    private int tripImg;
    private String tripName,tripType;


    public upcomingdata( int tripImg, String tripName, String tripType){

        this.tripImg=tripImg;
        this.tripName=tripName;
        this.tripType=tripType;


    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }


    public int getTripImg() {
        return tripImg;
    }

    public void setTripImg(int tripImg) {
        this.tripImg = tripImg;
    }



    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

}
