package com.kiwitaxi.vehicles.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;
import com.kiwitaxi.vehicles.data.remote.Resource;
import com.kiwitaxi.vehicles.data.remote.repository.VehicleRepository;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class FNVehicleListViewModel
 * @description : ViewModel class for Vehicle list
 * Created on 24/08/2019
 */
public class FNVehicleListViewModel extends ViewModel {
    private final LiveData<Resource<List<VehicleEntity>>> vehicleList;
    private LatLng latLng;

    @Inject
    public FNVehicleListViewModel(VehicleRepository vehicleRepository) {
        // Hard coding the values as of now. However, In real time the values should be fetched from some other sources.
        vehicleList = vehicleRepository.loadVehicles("53.694865","9.757589","53.394655","10.099891");
    }

    public LiveData<Resource<List<VehicleEntity>>> getVehicleList() {
        return vehicleList;
    }
    public void setCurrentLocation(LatLng latLng){
        this.latLng = latLng;

    }
    public LatLng getCurrentLocation(){
        return latLng;
    }
}
