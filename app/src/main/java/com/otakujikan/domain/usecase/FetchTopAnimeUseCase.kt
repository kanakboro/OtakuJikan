package com.otakujikan.domain.usecase

import com.google.gson.JsonElement
import com.otakujikan.domain.repository.fetchtopanime.FetchTopAnimeRepository
import com.otakujikan.domain.state.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchTopAnimeUseCase @Inject constructor(
    private val fetchTopAnimeRepository: FetchTopAnimeRepository
) {

    /**
     * Fetches the top anime.
     * @param isHardRefresh If true, forces a refresh from the API.
     * @return A Flow emitting UiState with the top anime data or an error message.
     */
    operator fun invoke(isHardRefresh: Boolean): Flow<UiState<JsonElement?>> =
        fetchTopAnimeRepository.fetchTopAnime(isHardRefresh)
}