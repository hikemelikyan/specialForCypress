package am.hikemelikyan.specialforcypress.shared.data.networking.root

import am.hikemelikyan.specialforcypress.CypressApp
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun isInternetAvailable(): Boolean {
    val connectivityManager = CypressApp.getInstance()
        ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val net = connectivityManager.activeNetwork
    return net?.let {
        val cap = connectivityManager.getNetworkCapabilities(it)
        cap != null && (cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || cap.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ))
    } ?: false
}