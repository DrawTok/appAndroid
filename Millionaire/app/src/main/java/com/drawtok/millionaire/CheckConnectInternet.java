package com.drawtok.millionaire;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

public class CheckConnectInternet {
    public static boolean isNetworkAvailable(Context context)
    {
        if(context == null)
        {
            return false;
        }
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager == null)
        {
            return false;
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        {
            Network network = manager.getActiveNetwork();
            if(network == null)
            {
                return false;
            }
            NetworkCapabilities capabilities = manager.getNetworkCapabilities(network);
            return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    ||capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        }else
        {
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
}
