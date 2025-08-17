package com.otakujikan.domain.usecase

import com.google.gson.JsonElement
import com.otakujikan.domain.repository.fetchanimedetail.FetchAnimeDetailRepository
import com.otakujikan.domain.state.UiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAnimeDetailUseCase @Inject constructor(
    private val fetchAnimeDetailRepository: FetchAnimeDetailRepository
) {
    /**
     * Fetches anime details by ID.
     *
     * @param id The ID of the anime to fetch details for.
     * @return A Flow emitting UiState with the anime details or an error message.
     */
    operator fun invoke(id: Int): Flow<UiState<JsonElement?>> =
        fetchAnimeDetailRepository.fetchAnimeDetailById(id)
}