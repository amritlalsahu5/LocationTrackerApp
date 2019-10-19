package com.kiwitaxi.vehicles.view.callbacks;

import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class VehicleResponseListener
 * @description :VehicleResponseListener for server calls.
 * Created on 24/08/2019
 */
public interface VehicleResponseListener {

    void onSuccess(VehicleEntity data);
    void onFailure(String message);
}
