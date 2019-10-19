package com.kiwitaxi.vehicles.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;

import java.util.List;



/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @interface VehicleDao
 * @description VehicleDao provides all the room  methods for vehicles table.
 * Created on 21/08/2019
 */

@Dao
public interface VehicleDao {
    @Query("SELECT * FROM vehicles")
    LiveData<List<VehicleEntity>> fetchNearByVehicles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveVehicles(List<VehicleEntity> vehicleEntities);

    @Query("DELETE FROM vehicles")
    void deleteAll();


}
