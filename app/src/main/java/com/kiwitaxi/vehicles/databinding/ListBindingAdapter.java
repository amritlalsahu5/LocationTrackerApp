package com.kiwitaxi.vehicles.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.kiwitaxi.vehicles.data.remote.Resource;
import com.kiwitaxi.vehicles.view.base.BaseAdapter;

import java.util.List;

/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class ListBindingAdapter
 * @description Binding adapters
 * Created on 21/08/2019
 */
final class ListBindingAdapter{

    private ListBindingAdapter(){
        // Private Constructor to hide the implicit one
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof BaseAdapter){
            ((BaseAdapter)adapter).setData((List) resource.data);
        }
    }

}
