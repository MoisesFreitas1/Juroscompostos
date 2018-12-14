package com.a2stars.juroscompostos.Models;

public class Receptor {
    public String elevationList;
    public String azimuteList;
    public String snrList;
    public double latitude;
    public double longitude;
    public String gmtNow;

    public Receptor (){
    }

    public String getElevationList() {
        return elevationList;
    }

    public void setElevationList(String elevationList) {
        this.elevationList = elevationList;
    }

    public String getAzimuteList() {
        return azimuteList;
    }

    public void setAzimuteList(String azimuteList) {
        this.azimuteList = azimuteList;
    }

    public String getSnrList() {
        return snrList;
    }

    public void setSnrList(String snrList) {
        this.snrList = snrList;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getGmtNow() {
        return gmtNow;
    }

    public void setGmtNow(String gmtNow) {
        this.gmtNow = gmtNow;
    }
}
