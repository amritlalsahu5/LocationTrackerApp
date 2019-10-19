package com.kiwitaxi.vehicles.di.builder;

import com.kiwitaxi.vehicles.view.fragment.VehicleDetailFragment;
import com.kiwitaxi.vehicles.view.fragment.VehicleListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class FragmentBuilderModule
 * @description This builder Module provides android injector service to fragments
 * Created on 21/08/2019
 */
@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract VehicleListFragment contributeVehicleListFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract VehicleDetailFragment contributeVehicleDetailFragment();

}
