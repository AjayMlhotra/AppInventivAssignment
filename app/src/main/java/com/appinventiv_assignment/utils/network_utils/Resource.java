package com.appinventiv_assignment.utils.network_utils;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    private final Status type;
    private final T data;
    private final Throwable error;
    private final String message;

    private Resource(@NonNull Status type, @Nullable T d, @Nullable Throwable e, @NonNull String message) {
        this.type = type;
        this.data = d;
        this.error = e;
        this.message = message;
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(Status.LOADING, null, null, "");
    }

    public static <T> Resource<T> success(T d) {
        return new Resource<>(Status.SUCCESS, d, null, "");
    }

    public static <T> Resource<T> error(Throwable e, String message) {
        return new Resource<>(Status.FAILURE, null, e, message);
    }

    public Status getType() {
        return type;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
   /* public class Loading extends Resource<T> { }
    public class Success extends Resource<T> {
        @NonNull
        T data;
        public Success(T data) {
            this.data = data;
        }
    }

    public class Failure extends Resource<T> {
        @NonNull
        String message;
        public Failure(String message) {
            this.message = message;
        }
    }*/
}