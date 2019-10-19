package com.kiwitaxi.vehicles.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.kiwitaxi.vehicles.R;
import com.kiwitaxi.vehicles.databinding.ActivityMainBinding;
import com.kiwitaxi.vehicles.utils.FragmentUtils;
import com.kiwitaxi.vehicles.view.base.BaseActivity;
import com.kiwitaxi.vehicles.view.fragment.VehicleListFragment;

import static com.kiwitaxi.vehicles.utils.FragmentUtils.TRANSITION_NONE;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class MainActivity
 * @description : MainActivity for the application
 * Created on 21/08/2019
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, VehicleListFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
