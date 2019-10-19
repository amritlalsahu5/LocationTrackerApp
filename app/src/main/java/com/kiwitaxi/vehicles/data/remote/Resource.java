package com.kiwitaxi.vehicles.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.kiwitaxi.vehicles.data.remote.Status.ERROR;
import static com.kiwitaxi.vehicles.data.remote.Status.LOADING;
import static com.kiwitaxi.vehicles.data.remote.Status.SUCCESS;


/**
 * @author Amrit Lal Sahu
 * @version 1.0
 * @Class Resource
 * @description A generic class that holds a value with its loading status.
 * Created on 21/08/2019
 */
public class Resource<T> {
    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable private final String message;

    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    @Nullable
    public String getMessage() {
        return message;
    }
}