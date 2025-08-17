package com.otakujikan.domain.provider.networkprovider

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.google.gson.JsonElement
import com.otakujikan.domain.state.UiState
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class NetworkProviderImpl @Inject constructor(
    @param:ApplicationContext val context: Context
) : NetworkProvider {

    /**
     * Checks if the device has an active internet connection.
     *
     * @return true if internet is available, false otherwise.
     */
    override fun isInternetAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(network) ?: return false
        return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    /**
     * Parses the API response and returns a UiState.
     *
     * @param response The Retrofit response containing the API data.
     * @return UiState containing the parsed data or an error message.
     */
    override fun parseApiResponse(response: Response<JsonElement>): UiState<JsonElement> {
        return if (response.isSuccessful) {
            response.body()?.let {
                UiState.Success(it)
            } ?: UiState.Error("Empty Response Body")
        } else {
            // Handle HTTP error codes
            when (response.code()) {
                401 -> UiState.SessionExpired("Session expired. Please log in again.")
                404 -> UiState.Error("Data not found.")
                in 500..599 -> UiState.Error("Server error. Please try later.")
                else -> UiState.Error("Unexpected error: ${response.code()}")
            }
        }
    }

}