package com.otakujikan.domain.repository.fetchanimedetail

import com.google.gson.JsonElement
import com.otakujikan.domain.state.UiState
import kotlinx.coroutines.flow.Flow

interface FetchAnimeDetailRepository {

    fun fetchAnimeDetailById(id: Int): Flow<UiState<JsonElement?>>

}