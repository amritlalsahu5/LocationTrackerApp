package com.kiwitaxi.vehicles.view.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class BaseFragment
 * @description : Generic Base Fragments for all the fragments
 * Created on 24/08/2019
 */
public abstract class BaseFragment<V extends ViewModel, D extends ViewDataBinding> extends Fragment {


    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected V viewModel;

    protected D dataBinding;

    protected abstract Class<V> getViewModel();

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(getViewModel());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return dataBinding.getRoot();
    }

}
