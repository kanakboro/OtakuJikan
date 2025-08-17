package com.otakujikan.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import com.otakujikan.R
import com.otakujikan.binding.RecyclerAdapter
import com.otakujikan.domain.model.fetchtopanime.DataItem
import com.otakujikan.domain.model.fetchtopanime.GenresItem
import com.otakujikan.domain.provider.gsonprovider.GsonProvider
import com.otakujikan.domain.provider.networkprovider.NetworkProvider
import com.otakujikan.domain.state.UiState
import com.otakujikan.domain.usecase.FetchAnimeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    private val networkProvider: NetworkProvider,
    private val animeDetailUseCase: FetchAnimeDetailUseCase,
    private val gsonProvider: GsonProvider
) : ViewModel() {

    val animeDetails: ObservableField<DataItem> = ObservableField()
    val adapter: RecyclerAdapter<GenresItem> = RecyclerAdapter(R.layout.row_genre)
    val isTrailerAvailable: ObservableField<Boolean> = ObservableField()

    private val _uiEvents = MutableSharedFlow<UiState<JsonElement?>>()
    val uiEvents = _uiEvents.asSharedFlow()

    private val _playerPlayBackListener = MutableSharedFlow<String?>()
    val playerPlayBackListener: SharedFlow<String?> = _playerPlayBackListener.asSharedFlow()

    fun fetchAnimeDetails() {
        viewModelScope.launch {
            animeDetails.get()?.malId?.let {
                animeDetailUseCase(it).collect { state ->
                    _uiEvents.emit(state)
                }
            }
        }
    }

    fun initData(animDataItem: DataItem) {
        viewModelScope.launch {
            animeDetails.set(animDataItem)
            animDataItem.apply {
                genres?.let {
                    adapter.addItems(it)
                }
                trailer?.youtubeId?.let {
                    if (networkProvider.isInternetAvailable()) {
                        isTrailerAvailable.set(true)
                        _playerPlayBackListener.emit(it)
                    }
                }
            }
        }
    }

    fun handleUpdatedData(jsonElement: JsonElement?) {
        viewModelScope.launch {
            jsonElement?.let {
                gsonProvider.getAnimeDetail(it)?.let { animeData ->
                    initData(animeData)
                }
            }
        }
    }

}