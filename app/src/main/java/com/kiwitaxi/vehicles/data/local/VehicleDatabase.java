package com.kiwitaxi.vehicles.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.kiwitaxi.vehicles.data.local.dao.VehicleDao;
import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;

/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class VehicleDatabase
 * @description Custom RoomDatabase class provides all the required Dao objects.
 * Created on 21/08/2019
 */
@Database(entities = {VehicleEntity.class}, version = 1)
public abstract class VehicleDatabase extends RoomDatabase {
    public abstract VehicleDao fnVehicleDao();
}