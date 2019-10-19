package com.kiwitaxi.vehicles.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kiwitaxi.vehicles.R;
import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;
import com.kiwitaxi.vehicles.databinding.ItemVehicleListBinding;
import com.kiwitaxi.vehicles.model.FleetType;
import com.kiwitaxi.vehicles.utils.LocationUtils;
import com.kiwitaxi.vehicles.view.base.BaseAdapter;
import com.kiwitaxi.vehicles.view.callbacks.VehicleListCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class VehicleListAdapter
 * @description : This class represents the Vehicle list recyclerview adapter
 * Created on 24/08/2019
 */
public class VehicleListAdapter extends  BaseAdapter<VehicleListAdapter.VehicleViewHolder, VehicleEntity> {

    private List<VehicleEntity> vehicleEntities;
    private LatLng referenceLocation;


    private final VehicleListCallback vehicleListCallback;

    public VehicleListAdapter(@NonNull VehicleListCallback vehicleListCallback, LatLng referenceLocation) {
        vehicleEntities = new ArrayList<>();
        this.vehicleListCallback = vehicleListCallback;
        this.referenceLocation = referenceLocation;
    }

    @Override
    public void setData(List<VehicleEntity> entities) {
        this.vehicleEntities = entities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return VehicleViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, vehicleListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder viewHolder, int i) {
        viewHolder.onBind(vehicleEntities.get(i));
        double distance = LocationUtils.getDistanceBetweenLocations(referenceLocation.latitude,referenceLocation.longitude,viewHolder.binding.getVehicle().getLatitude(),viewHolder.binding.getVehicle().getLongitude());
        String distanceStr = LocationUtils.getDistanceText(distance);
        vehicleEntities.get(i).setContent(distanceStr);
        viewHolder.binding.timeTxt.setText(distanceStr);
        viewHolder.binding.titleTxt.setText(viewHolder.binding.getVehicle().getFleetType());
        if(viewHolder.binding.getVehicle().getFleetType().equals(FleetType.POOLING.name())){
            viewHolder.binding.imageView.setBackgroundResource(R.drawable.ic_pool);
        }else{
            viewHolder.binding.imageView.setBackgroundResource(R.drawable.ic_taxi);
        }
    }

    @Override
    public int getItemCount() {
        return vehicleEntities.size();
    }


    static class VehicleViewHolder extends RecyclerView.ViewHolder {

        private static VehicleViewHolder create(LayoutInflater inflater, ViewGroup parent, VehicleListCallback callback) {
            ItemVehicleListBinding itemMovieListBinding = ItemVehicleListBinding.inflate(inflater, parent, false);
            return new VehicleViewHolder(itemMovieListBinding, callback);
        }

        final ItemVehicleListBinding binding;

        private VehicleViewHolder(ItemVehicleListBinding binding, VehicleListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onVehicleClicked(binding.getVehicle(),this.getAdapterPosition()));

        }

        private void onBind(VehicleEntity vehicleEntity) {
            binding.setVehicle(vehicleEntity);
            binding.executePendingBindings();
        }
    }
}
