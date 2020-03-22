package com.naci.mytestapplication.data.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    @Nullable
    public final Throwable error;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.error = null;
        this.message = message;
    }

    public Resource(@NonNull Status status, @Nullable Throwable error, @Nullable String message) {
        this.status = status;
        this.data = null;
        this.error = error;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(@NonNull String msg, @Nullable Throwable error) {
        return new Resource<>(Status.ERROR, error, msg);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(Status.LOADING, null, null);
    }

    public static <T> Resource<T> completed() {
        return new Resource<>(Status.COMPLETED, null, null);
    }

    public enum Status {SUCCESS, ERROR, LOADING, COMPLETED}
}
