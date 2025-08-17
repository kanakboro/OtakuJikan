package com.otakujikan.domain.provider.networkprovider

import com.google.gson.JsonElement
import com.otakujikan.domain.state.UiState
import retrofit2.Response

interface NetworkProvider {
    fun isInternetAvailable(): Boolean

    fun parseApiResponse(response: Response<JsonElement>) : UiState<JsonElement>
}