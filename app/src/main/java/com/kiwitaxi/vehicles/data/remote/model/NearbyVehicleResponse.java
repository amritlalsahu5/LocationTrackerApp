package com.kiwitaxi.vehicles.data.remote.model;

import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class NearbyVehicleResponse
 * @description The model class which holds the nearby vehicles data from server.
 * Created on 21/08/2019
 */
public class NearbyVehicleResponse {



    @SerializedName("poiList")
    private List<VehicleEntity> nearbyVehicles;

    /**
     * This method return the list of vehicle entities
     * @return List of entities
     */
    public List<VehicleEntity> getnearbyVehicles() {
        return nearbyVehicles;
    }

    /**
     * This method sets the vehicle entities
     * @param nearbyVehicles - vehicleslist
     */
    @SuppressWarnings("unused")
    public void setnearbyVehicles(List<VehicleEntity> nearbyVehicles) {
        this.nearbyVehicles = nearbyVehicles;
    }
}
