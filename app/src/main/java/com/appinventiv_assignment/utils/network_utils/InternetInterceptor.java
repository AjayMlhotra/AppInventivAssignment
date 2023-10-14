package com.appinventiv_assignment.utils.network_utils;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class InternetInterceptor implements Interceptor {
    private final NetworkHelper networkMonitor;
    @Inject
    public InternetInterceptor(NetworkHelper networkMonitor) {
        this.networkMonitor = networkMonitor;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        if (networkMonitor.isNetworkConnected()) {
            Request oldRequest = chain.request();
            return chain.proceed(oldRequest);
        } else {
            throw new NoNetworkException();
        }
    }
}