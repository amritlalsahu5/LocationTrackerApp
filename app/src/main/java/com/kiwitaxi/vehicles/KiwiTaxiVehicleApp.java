package com.kiwitaxi.vehicles;

import android.app.Activity;
import android.app.Application;

import com.kiwitaxi.vehicles.di.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class KiwiTaxiVehicleApp
 * @description : Application class for the app.
 * Created on 24/08/2019
 */

public class KiwiTaxiVehicleApp extends Application implements HasActivityInjector {

    private static KiwiTaxiVehicleApp sInstance;


    public static KiwiTaxiVehicleApp getAppContext() {
        return sInstance;
    }



    private static synchronized void setInstance(KiwiTaxiVehicleApp app) {
        sInstance = app;
    }
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
