package com.kiwitaxi.vehicles.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.kiwitaxi.vehicles.viewmodel.FNVehicleListViewModel;
import com.kiwitaxi.vehicles.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class ViewModelModule
 * @description :Allow us to inject dependencies via constructor injection
 * Created on 24/08/2019
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FNVehicleListViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsVehicleListViewModel(FNVehicleListViewModel vehicleListViewModel);


    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
