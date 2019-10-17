package com.kotlin.mvvm.remote

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.kotlin.mvvm.KtApplication
import com.kotlin.mvvm.presentation.extension.connectivityManager

object NetworkHandler {

    fun isConnected(): Boolean {
        val connectivityManager = KtApplication.getContext().connectivityManager

        return when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            true -> advancedVersionJudgment(connectivityManager)
            false -> lowerVersionJudgment(connectivityManager)
        }

    }

    private fun advancedVersionJudgment(cm: ConnectivityManager?): Boolean {
        cm?.run {
            cm?.getNetworkCapabilities(cm?.activeNetwork)?.run {
                return when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return false
    }


    private fun lowerVersionJudgment(cm: ConnectivityManager?): Boolean {
        cm?.run {
            cm?.activeNetworkInfo?.run {
                return when {
                    (type == ConnectivityManager.TYPE_WIFI) -> true
                    (type == ConnectivityManager.TYPE_MOBILE) -> true
                    else -> false
                }
            }
        }
        return false
    }
}