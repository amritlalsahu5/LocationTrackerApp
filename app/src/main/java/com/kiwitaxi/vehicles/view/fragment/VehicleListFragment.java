package com.kiwitaxi.vehicles.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiwitaxi.vehicles.R;
import com.kiwitaxi.vehicles.common.AppConstants;
import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;
import com.kiwitaxi.vehicles.data.remote.Status;
import com.kiwitaxi.vehicles.databinding.FragmentListVehiclesBinding;
import com.kiwitaxi.vehicles.utils.FragmentUtils;
import com.kiwitaxi.vehicles.utils.LocationUtils;
import com.kiwitaxi.vehicles.view.adapter.VehicleListAdapter;
import com.kiwitaxi.vehicles.view.base.BaseFragment;
import com.kiwitaxi.vehicles.view.callbacks.VehicleListCallback;
import com.kiwitaxi.vehicles.viewmodel.FNVehicleListViewModel;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class VehicleListFragment
 * @description :VehicleListFragment will provide all the list of vehicles nearby for some provided location.
 * Created on 24/08/2019
 */
public class VehicleListFragment extends BaseFragment<FNVehicleListViewModel, FragmentListVehiclesBinding> implements VehicleListCallback {

    public static VehicleListFragment newInstance() {
        Bundle args = new Bundle();
        VehicleListFragment fragment = new VehicleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<FNVehicleListViewModel> getViewModel() {
        return FNVehicleListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list_vehicles;
    }

    @Override
    public void onVehicleClicked(VehicleEntity vehicleEntity, int position) {
        if(null != getActivity()){
            Bundle args = new Bundle();
            args.putInt(AppConstants.BUNDLE_KEY_SELECTED_VEHICLE_LIST_POSITION, position);
            VehicleDetailFragment detailFragment = new VehicleDetailFragment();
            detailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), detailFragment, R.id.fragContainer, true, FragmentUtils.TRANSITION_SLIDE_LEFT_RIGHT);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new VehicleListAdapter(this,LocationUtils.getReferenceLocation()));
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        viewModel.getVehicleList()
                .observe(this, listResource -> {
                    if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                        dataBinding.loginProgress.setVisibility(View.GONE);
                    }
                    dataBinding.setResource(listResource);

                    // If the cached data is already showing then no need to show the error
                    if(null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0){
                        dataBinding.errorText.setVisibility(View.GONE);
                    }
                });
        viewModel.setCurrentLocation(LocationUtils.getReferenceLocation());



    }




}
