package com.kiwitaxi.vehicles.di.components;

import android.app.Application;

import com.kiwitaxi.vehicles.KiwiTaxiVehicleApp;
import com.kiwitaxi.vehicles.di.builder.ActivityBuilderModule;
import com.kiwitaxi.vehicles.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @interface AppComponent
 * @description The main application component which initializes all the dependent modules
 * Created on 21/08/2019
 */
@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {//
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(KiwiTaxiVehicleApp kiwiTaxiVehicleApp);
}