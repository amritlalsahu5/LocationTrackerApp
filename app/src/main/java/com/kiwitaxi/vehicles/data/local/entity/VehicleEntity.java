package com.kiwitaxi.vehicles.data.local.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.kiwitaxi.vehicles.data.local.entity.vehicledata.Coordinate;
import com.google.gson.annotations.SerializedName;

/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class VehicleEntity
 * @description This entity class stores object of vehicles tables and same used to parse data from server
 * Created on 21/08/2019
 */
@Entity(tableName = "vehicles")
public class VehicleEntity {





    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("titleInfo")
    private String titleInfo;



    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(String titleInfo) {
        this.titleInfo = titleInfo;
    }

    @Ignore
    @SerializedName("coordinate")
    private Coordinate coordinate;

    @SerializedName("fleetType")
    private String fleetType;

    @SerializedName("heading")
    private double heading;

    @ColumnInfo(name = "latitude")
    private double latitude;

    @ColumnInfo(name = "longitude")
    private double longitude;


    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setFleetType(String fleetType) {
        this.fleetType = fleetType;
    }

    public String getFleetType() {
        return this.fleetType;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getHeading() {
        return this.heading;
    }



    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLatitude() {
        return coordinate != null ? coordinate.getLatitude() : latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return coordinate != null ? coordinate.getLongitude(): longitude;
    }

}
