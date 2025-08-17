package com.otakujikan.domain.repository.fetchtopanime

import com.core.db.dao.AnimeDao
import com.core.db.entity.AnimeEntity
import com.core.network.ApiService
import com.google.gson.JsonElement
import com.otakujikan.domain.provider.networkprovider.NetworkProvider
import com.otakujikan.domain.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchTopAnimeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkProvider: NetworkProvider,
    private val animeDao: AnimeDao
) : FetchTopAnimeRepository {

    /**
     * Fetches the top anime from the API or local database.
     *
     * @param isHardRefresh If true, forces a refresh from the API.
     * @return A Flow emitting UiState with the top anime data or an error message.
     */
    override fun fetchTopAnime(isHardRefresh: Boolean): Flow<UiState<JsonElement>> = flow {
        emit(UiState.Loading)
        val anime = animeDao.getTopAnime()
            .catch {}
            .firstOrNull().orEmpty()

        if (!isHardRefresh) {
            if (anime.isNotEmpty()) {
                emit(UiState.Success(anime[0].topAnimeData))
            }
        }

        if (networkProvider.isInternetAvailable()) {
            val response = apiService.fetchTopAnime()
            emit(networkProvider.parseApiResponse(response))
            if (response.isSuccessful) {
                response.body()?.let {
                    if (anime.isNotEmpty()) {
                        animeDao.updateTopAnime(AnimeEntity(anime[0].id, it))
                    } else {
                        animeDao.insertTopAnime(AnimeEntity(topAnimeData = it))
                    }
                }
            }
        } else {
            emit(UiState.Error("No Network Available"))
        }
    }.catch { e ->
        emit(UiState.Error(e.message ?: ""))
    }.flowOn(Dispatchers.IO)

}