package com.kiwitaxi.vehicles.utils;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class LocationUtils
 * @description : Utils class provides location related methods.
 * Created on 24/08/2019
 */
public class LocationUtils {

    /*
        Calculates distance between two coordinates in Kms.
     */
    public static double getDistanceBetweenLocations(double sourceLat,double sourceLon, double destLat, double destLon){
        float[] results = new float[1];
        Location.distanceBetween(sourceLat, sourceLon,
                destLat, destLon, results);
        return results[0]/1000;

    }
    /*
        Distance text with proper formatting to be shown in different scenarios.
        In real scenarios, unit conversion should also be handled
     */
    public static String getDistanceText(double distanceInMeter){
        return new DecimalFormat("####0.00").format(distanceInMeter) +" km Away";


    }

    /*
    In real time this location may be retrieved from selected location on google Map or current location
    In this scenario, reference Hamburg location provided in the assignment is used.
 */
    public static  LatLng getReferenceLocation(){
        LatLng latLng = new LatLng(53.694865,9.757589);
        return latLng;

    }
}
