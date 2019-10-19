package com.kiwitaxi.vehicles.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.kiwitaxi.vehicles.R;
import com.kiwitaxi.vehicles.common.AppConstants;
import com.kiwitaxi.vehicles.data.local.entity.VehicleEntity;
import com.kiwitaxi.vehicles.databinding.FragmentVehiclesDetailsBinding;
import com.kiwitaxi.vehicles.model.FleetType;
import com.kiwitaxi.vehicles.utils.LocationUtils;
import com.kiwitaxi.vehicles.view.base.BaseFragment;
import com.kiwitaxi.vehicles.view.callbacks.OnMapAndViewReadyListener;
import com.kiwitaxi.vehicles.viewmodel.FNVehicleListViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class VehicleDetailFragment
 * @description :The Vehicle Details fragment which is responsible for showing the Vehicle location details
 * Created on 24/08/2019
 */
public class VehicleDetailFragment extends BaseFragment<FNVehicleListViewModel, FragmentVehiclesDetailsBinding> implements
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerDragListener,
        OnInfoWindowLongClickListener,
        GoogleMap.OnInfoWindowCloseListener,
        OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener{

    private GoogleMap mMap;
    private static final double DEFAULT_RADIUS_METERS = 1000;


    private int selectedLocationPosition;
    @Override
    protected Class<FNVehicleListViewModel> getViewModel() {
        return FNVehicleListViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_vehicles_details;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if(null != args) {
            selectedLocationPosition = args.getInt(AppConstants.BUNDLE_KEY_SELECTED_VEHICLE_LIST_POSITION);
        }

        SupportMapFragment mapFragment =  ((SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map));

        new OnMapAndViewReadyListener(mapFragment, this);

    }

    //**************************************
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Hide the zoom controls as the button panel will cover it.
        mMap.getUiSettings().setZoomControlsEnabled(false);



        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnInfoWindowCloseListener(this);
        mMap.setOnInfoWindowLongClickListener(this);mMap.getUiSettings().setScrollGesturesEnabled(true);

        // Override the default content description on the view, for accessibility mode.
        // Ideally this string would be localised.
        mMap.setContentDescription("Map with lots of markers.");

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(getAllMarkersBounds(), 300));

        List<VehicleEntity> vehicleEntityList = viewModel.getVehicleList().getValue().data;
        LatLng selectedLocation = new LatLng(vehicleEntityList.get(selectedLocationPosition).getLatitude(), vehicleEntityList.get(selectedLocationPosition).getLongitude());


        mMap.addCircle(new CircleOptions()
                .center(selectedLocation)
                .radius(DEFAULT_RADIUS_METERS)
                .strokeColor(Color.BLUE)
                .fillColor(ContextCompat.getColor(getContext(),R.color.color_marker_circle)));


    }

    private LatLngBounds getAllMarkersBounds(){

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        LatLng latLng;
        List<VehicleEntity> vehicleEntityList = viewModel.getVehicleList().getValue().data;
        for(int i = 0; i < vehicleEntityList.size();i++){
            latLng= new LatLng(vehicleEntityList.get(i).getLatitude(), vehicleEntityList.get(i).getLongitude());
            builder.include(latLng);
            addSelectedMarkerToMap(latLng,vehicleEntityList.get(i));
        }

        return  builder.build();

    }
    private void addSelectedMarkerToMap(LatLng latLng, VehicleEntity vehicleEntity) {
        float bitmapDescType;
        if(vehicleEntity.getFleetType().equals(FleetType.POOLING.name())){
            bitmapDescType = BitmapDescriptorFactory.HUE_GREEN;
        }else{
            bitmapDescType = BitmapDescriptorFactory.HUE_YELLOW;
        }
        double distance = LocationUtils.getDistanceBetweenLocations(LocationUtils.getReferenceLocation().latitude,LocationUtils.getReferenceLocation().longitude,
                vehicleEntity.getLatitude(),vehicleEntity.getLongitude());
        String distanceStr = LocationUtils.getDistanceText(distance);
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(vehicleEntity.getFleetType())
                .snippet(distanceStr)
                .icon(BitmapDescriptorFactory.defaultMarker(bitmapDescType)));

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

            // This causes the marker at Perth to bounce into position when it is clicked.
            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            final long duration = 1500;

            final Interpolator interpolator = new BounceInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = Math.max(
                            1 - interpolator.getInterpolation((float) elapsed / duration), 0);
                    marker.setAnchor(0.5f, 1.0f + 2 * t);

                    if (t > 0.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 16);
                    }
                }
            });

        // We return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getContext(), "Click Info Window", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInfoWindowClose(Marker marker) {
        //Toast.makeText(this, "Close Info Window", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {
        Toast.makeText(getContext(), "Info Window long click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
    }

    @Override
    public void onMarkerDrag(Marker marker) {
    }
}
