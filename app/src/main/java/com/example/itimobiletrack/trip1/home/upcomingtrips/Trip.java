package com.example.itimobiletrack.trip1.home.upcomingtrips;

/**
 * Created by ahmed on 2/14/2017.
 */

public class Trip {


    private String tripName, sPoint, epoint,strnotes,strdate,strtime;
    private int tripid,round,onedirection;
    private int status;

    public Trip() {
    }

    public Trip(int id, String tripName, String sPoint) {

        this.tripName = tripName;
        //  this.tripType=tripType;
        this.sPoint = sPoint;
        //  this.epoint=ePoint;
        this.tripid = id;

    }

    public Trip(String tripName, String sPoint, String epoint, String strnotes, String strdate, String strtime, int round, int onedirection, int status){
        this.tripName=tripName;
        this.sPoint=sPoint;
        this.epoint=epoint;
        this.strnotes=strnotes;
        this.strdate=strdate;
        this.strtime=strtime;
        this.round=round;
        this.onedirection=onedirection;
        this.status=status;


    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStrnotes() {
        return strnotes;
    }

    public void setStrnotes(String strnotes) {
        this.strnotes = strnotes;
    }

    public String getStrdate() {
        return strdate;
    }

    public void setStrdate(String strdate) {
        this.strdate = strdate;
    }

    public String getStrtime() {
        return strtime;
    }

    public void setStrtime(String strtime) {
        this.strtime = strtime;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getOnedirection() {
        return onedirection;
    }

    public void setOnedirection(int onedirection) {
        this.onedirection = onedirection;
    }

    public int getTripid() {
        return tripid;
    }

    public void setTripid(int tripid) {
        this.tripid = tripid;
    }

    public String getEpoint() {
        return epoint;
    }

    public void setEpoint(String epoint) {
        this.epoint = epoint;
    }

    public String getsPoint() {
        return sPoint;
    }

    public void setsPoint(String sPoint) {
        this.sPoint = sPoint;
    }


    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

}
