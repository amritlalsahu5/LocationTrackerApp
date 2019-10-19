package com.kiwitaxi.vehicles.data.local.entity.vehicledata;



/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class Coordinate
 * @description POJO class used to store latitude and longitude.
 * Created on 21/08/2019
 */
public class Coordinate {
    private double latitude;

    private double longitude;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
