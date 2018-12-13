package com.topeomot.youtubekeywordapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject


class Connectivity @Inject constructor() {

    /**
     * Get the network info
     * @param context
     * @return
     */
    fun getNetworkInfo(context: Context): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    /**
     * Check if there is any connectivity
     * @param context
     * @return
     */
    fun isConnected(context: Context): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info!!.isConnected()
    }

    /**
     * Check if there is any connectivity to a Wifi network
     * @param context
     * @return
     */
    fun isConnectedWifi(context: Context): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info!!.isConnected && info!!.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * Check if there is any connectivity to a mobile network
     * @param context
     * @return
     */
    fun isConnectedMobile(context: Context): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info!!.isConnected && info!!.type == ConnectivityManager.TYPE_MOBILE
    }

}