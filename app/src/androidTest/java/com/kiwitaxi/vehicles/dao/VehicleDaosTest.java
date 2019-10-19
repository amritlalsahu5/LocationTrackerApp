package com.kiwitaxi.vehicles.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.kiwitaxi.vehicles.data.local.VehicleDatabase;
import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class VehicleDaosTest {

    private VehicleDatabase vehicleDatabase;

    @Before
    public void init(){
        vehicleDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), VehicleDatabase.class).build();
    }

    @After
    public void uninit(){
        vehicleDatabase.close();
    }
    @Test
    public void testLoadNearbyVehicles(){
        List<VehicleEntity> vehicleEntities = new ArrayList<>();
        VehicleEntity entity = new VehicleEntity();
        entity.setId(1000);
        entity.setTitleInfo("test");
        entity.setLatitude(53.694865);
        entity.setLongitude(9.757589);
        entity.setFleetType("POOLING");
        vehicleEntities.add(entity);
        vehicleDatabase.fnVehicleDao().saveVehicles(vehicleEntities);
        LiveData<List<VehicleEntity>> vehiclesList =  vehicleDatabase.fnVehicleDao().fetchNearByVehicles();
        assertNotNull(vehiclesList);
    }

}
