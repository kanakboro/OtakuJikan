package com.otakujikan.domain.repository.fetchtopanime

import com.google.gson.JsonElement
import com.otakujikan.domain.state.UiState
import kotlinx.coroutines.flow.Flow

interface FetchTopAnimeRepository {
    fun fetchTopAnime(isHardRefresh: Boolean): Flow<UiState<JsonElement?>>
}