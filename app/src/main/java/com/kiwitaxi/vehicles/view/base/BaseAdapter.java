package com.kiwitaxi.vehicles.view.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class BaseAdapter
 * @description :Generic Base adapter for recycler views
 * Created on 24/08/2019
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<T>{

    public abstract void setData(List<D> data);
}