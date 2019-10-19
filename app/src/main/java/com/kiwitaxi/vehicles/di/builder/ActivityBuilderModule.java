package com.kiwitaxi.vehicles.di.builder;

import com.kiwitaxi.vehicles.view.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class ActivityBuilderModule
 * @description The module which provides the android injection service to Activities.
 * Created on 21/08/2019
 */
@Module
public abstract class ActivityBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();


}
