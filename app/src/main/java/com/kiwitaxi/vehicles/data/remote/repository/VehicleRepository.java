package com.kiwitaxi.vehicles.data.remote.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.kiwitaxi.vehicles.data.local.dao.VehicleDao;
import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;
import com.kiwitaxi.vehicles.data.remote.FNApiService;
import com.kiwitaxi.vehicles.data.remote.NetworkBoundResource;
import com.kiwitaxi.vehicles.data.remote.Resource;
import com.kiwitaxi.vehicles.data.remote.model.NearbyVehicleResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class VehicleRepository
 * @description The Vehicle repository which has access to local and remote data fetching services
 * Created on 22/08/2019
 */
public class VehicleRepository {

    private final VehicleDao vehicleDao;
    private final FNApiService apiService;

    @Inject
    VehicleRepository(VehicleDao dao, FNApiService service) {
        this.vehicleDao = dao;
        this.apiService = service;
    }

    /**
     * This method fetches the popular vehicles from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     * @return List of vehicles
     */
    public LiveData<Resource<List<VehicleEntity>>> loadVehicles(String lat1,String lon1, String lat2, String lon2) {
        return new NetworkBoundResource<List<VehicleEntity>, NearbyVehicleResponse>() {

            @Override
            protected void saveCallResult(NearbyVehicleResponse item) {
                if(null != item) {
                    vehicleDao.deleteAll();
                    vehicleDao.saveVehicles(item.getnearbyVehicles());
                }
            }

            @NonNull
            @Override
            protected LiveData<List<VehicleEntity>> loadFromDb() {
                return vehicleDao.fetchNearByVehicles();
            }

            @NonNull
            @Override
            protected Call<NearbyVehicleResponse> createCall() {
                return apiService.loadAllVehicles(lat1,lon1,lat2,lon2);
            }
        }.getAsLiveData();
    }

}
