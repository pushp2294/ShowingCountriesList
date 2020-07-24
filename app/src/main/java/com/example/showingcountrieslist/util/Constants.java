package com.example.showingcountrieslist.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constants {
public static  String BASEURL="https://restcountries.eu/";

    public  static boolean isNetworkAvailable(Context _context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
