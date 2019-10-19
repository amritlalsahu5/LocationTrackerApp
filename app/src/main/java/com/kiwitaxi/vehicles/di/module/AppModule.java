package com.kiwitaxi.vehicles.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.kiwitaxi.vehicles.data.local.VehicleDatabase;
import com.kiwitaxi.vehicles.data.local.dao.VehicleDao;
import com.kiwitaxi.vehicles.data.remote.ApiConstants;
import com.kiwitaxi.vehicles.data.remote.FNApiService;
import com.kiwitaxi.vehicles.data.remote.RequestInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class AppModule
 * @description The application module which provides app wide instances of various components
 * Created on 21/08/2019
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }


    @Provides
    @Singleton
    FNApiService provideFNRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.FN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(FNApiService.class);
    }

    @Provides
    @Singleton
    VehicleDatabase provideVehicleDatabase(Application application) {
        return Room.databaseBuilder(application, VehicleDatabase.class, "vehicles.db").build();
    }


    @Provides
    @Singleton
    VehicleDao provideFNVehicleDao(VehicleDatabase vehicleDatabase) {
        return vehicleDatabase.fnVehicleDao();
    }

}
