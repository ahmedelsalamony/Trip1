package com.example.itimobiletrack.trip1;

/**
 * Created by ahmed on 2/14/2017.
 */

public class upcomingdata {

    private int profileImg,tripImg;
    private String userName,tripName,tripType;


    public upcomingdata(int profileImg, int tripImg, String userName, String tripName, String tripType){
       this.profileImg=profileImg;
        this.tripImg=tripImg;
        this.userName=userName;
        this.tripName=tripName;
        this.tripType=tripType;


    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public int getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(int profileImg) {
        this.profileImg = profileImg;
    }

    public int getTripImg() {
        return tripImg;
    }

    public void setTripImg(int tripImg) {
        this.tripImg = tripImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

}
