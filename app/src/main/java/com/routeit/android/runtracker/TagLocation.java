package com.raywenderlich.android.runtracker;

public class TagLocation {
    private String latitude;
    private String longitude;
    private String z;
    private String speed;
    private String timestamp;
    private String metadata;

    public TagLocation(String latitude, String longitude, String z, String speed, String timestamp, String metadata) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
