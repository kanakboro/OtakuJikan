package com.otakujikan.domain.repository.fetchanimedetail

import com.core.network.ApiService
import com.google.gson.JsonElement
import com.otakujikan.domain.provider.networkprovider.NetworkProvider
import com.otakujikan.domain.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchAnimeDetailRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkProvider: NetworkProvider
) : FetchAnimeDetailRepository {

    /**
     * Fetches anime details by ID.
     *
     * @param id The ID of the anime to fetch details for.
     * @return A Flow emitting UiState with the anime details or an error message.
     */
    override fun fetchAnimeDetailById(id: Int): Flow<UiState<JsonElement?>> =
        flow {
            emit(UiState.Loading)
            if (networkProvider.isInternetAvailable()) {
                val response = apiService.getAnimeDetail(id)
                emit(networkProvider.parseApiResponse(response))
            } else {
                emit(UiState.Error("No Network Available"))
            }
        }.catch { e ->
            emit(UiState.Error(e.message ?: ""))
        }.flowOn(Dispatchers.IO)
}