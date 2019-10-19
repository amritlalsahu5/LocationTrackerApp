package com.kiwitaxi.vehicles.data.remote;




/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class ApiConstants
 * @description Keep all the service related constants here.
 * Created on 22/08/2019
 */
public class ApiConstants {
    public static final String FN_BASE_URL = "https://fake-poi-api.mytaxi.com";
    public static final long CONNECT_TIMEOUT = 30000;
    public static final long READ_TIMEOUT = 30000;
    public static final long WRITE_TIMEOUT = 30000;

    private ApiConstants(){
        // Private constructor to hide the implicit one
    }

}
