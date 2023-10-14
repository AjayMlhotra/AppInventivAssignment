package com.appinventiv_assignment.utils.network_utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import javax.inject.Inject;
import javax.inject.Singleton;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class NetworkHelper {
    private final Context context;

    @Inject
    NetworkHelper(@ApplicationContext Context context) {
        this.context = context;
    }

    public boolean isNetworkConnected() {
        boolean result;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network networkCapabilities = connectivityManager.getActiveNetwork();
        if (networkCapabilities == null) {
            return false;
        }
        NetworkCapabilities activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities);
        if (activeNetwork == null) {
            return false;
        }
        result = activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
        return result;
    }
}
