package com.kiwitaxi.vehicles.view.callbacks;

import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;

/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class VehicleListCallback
 * @description : Callback Class.
 * Created on 24/08/2019
 */
public interface VehicleListCallback {
    void onVehicleClicked(VehicleEntity vehicleEntity, int position);
}

