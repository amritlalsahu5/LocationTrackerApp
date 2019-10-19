package com.kiwitaxi.vehicles.api;

import com.kiwitaxi.vehicles.data.remote.ApiConstants;
import com.kiwitaxi.vehicles.data.remote.FNApiService;
import com.kiwitaxi.vehicles.data.remote.RequestInterceptor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ApiServiceUnitTest {

   private FNApiService apiService;


    @Before
    public void createService() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        apiService = new Retrofit.Builder()
                .baseUrl(ApiConstants.FN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(FNApiService.class);
    }


    @Test
    public void getNearbyVehicles() {
        try {
            Response response = apiService.loadAllVehicles("53.694865","9.757589","53.394655","10.099891").execute();
            assertEquals(response.code(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
