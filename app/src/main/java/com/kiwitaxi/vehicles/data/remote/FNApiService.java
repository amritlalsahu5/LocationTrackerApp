package com.kiwitaxi.vehicles.data.remote;

import com.kiwitaxi.vehicles.data.remote.model.NearbyVehicleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @interface FNApiService
 * @description The APIService interface which will contain the semantics of all the REST calls.
 * Created on 22/08/2019
 */
public interface FNApiService {

    @GET("/")
    Call<NearbyVehicleResponse> loadAllVehicles(@Query("p1Lat")  String lat1, @Query("p1Lon")  String lon1, @Query("p2Lat")  String lat2, @Query("p2Lon")  String lon2);



}
