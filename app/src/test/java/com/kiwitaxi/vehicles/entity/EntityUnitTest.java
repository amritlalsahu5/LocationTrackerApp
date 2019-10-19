package com.kiwitaxi.vehicles.entity;

import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class EntityUnitTest {


    @Test
    public void testTitle(){
        VehicleEntity fNVehicleEntity = new VehicleEntity();
        fNVehicleEntity.setTitleInfo("test");
        assertEquals(fNVehicleEntity.getTitleInfo(), "test");
    }

    @Test
    public void testContent(){
        VehicleEntity FNVehicleEntity = new VehicleEntity();
        FNVehicleEntity.setContent("test");
        assertEquals(FNVehicleEntity.getContent(), "test");
    }

    @Test
    public void testFleetType(){
        VehicleEntity fNVehicleEntity = new VehicleEntity();
        fNVehicleEntity.setFleetType("POOLING");
        assertEquals(fNVehicleEntity.getFleetType(), "POOLING");
    }

}
