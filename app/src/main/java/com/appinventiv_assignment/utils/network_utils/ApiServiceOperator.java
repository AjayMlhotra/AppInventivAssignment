package com.appinventiv_assignment.utils.network_utils;

import android.content.Context;
import androidx.annotation.NonNull;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiServiceOperator<T> implements Callback<T> {

    private Context context;

    public interface OnResponseListener<T> {
        void onSuccess(T body);

        void onFailure(Throwable t, String message);
    }

    private OnResponseListener<T> onResponseListener;

    @Inject
    public ApiServiceOperator(OnResponseListener<T> onResponseListener) {
        this.onResponseListener = onResponseListener;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) { // here, do the extraction of body
            onResponseListener.onSuccess(response.body());
        } else {
            onResponseListener.onFailure(new ServerErrorException(), "We are getting network error, Please try again later."/*context.getString(R.string.err_network)*/);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        if (t instanceof NoNetworkException) {
            onResponseListener.onFailure(t, "Please check your internet connection and try again later.");
        } else {
            onResponseListener.onFailure(new ConnectionErrorException(), "We are getting network error, Please try again later.");
        }
    }

    // these exception can be on a separate classes.
    public static class ServerErrorException extends Exception {
    }

    public static class ConnectionErrorException extends Exception {
    }
}