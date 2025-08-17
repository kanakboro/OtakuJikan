package com.otakujikan.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import com.otakujikan.R
import com.otakujikan.binding.RecyclerAdapter
import com.otakujikan.domain.model.fetchtopanime.DataItem
import com.otakujikan.domain.provider.gsonprovider.GsonProvider
import com.otakujikan.domain.state.UiState
import com.otakujikan.domain.usecase.FetchTopAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchTopAnimeUseCase: FetchTopAnimeUseCase,
    private val gsonProvider: GsonProvider
) : ViewModel() {

    val isErrorShow: ObservableField<Boolean> = ObservableField(false)
    private val _listItemClickListener = MutableSharedFlow<DataItem?>()
    val listItemClickListener: SharedFlow<DataItem?> = _listItemClickListener.asSharedFlow()

    private val _uiEvents = MutableSharedFlow<UiState<JsonElement?>>()
    val uiEvents = _uiEvents.asSharedFlow()

    val adapter: RecyclerAdapter<DataItem> = RecyclerAdapter(R.layout.row_anime)

    init {
        adapter.setOnItemClick { _, pos, type ->
            viewModelScope.launch {
                _listItemClickListener.emit(adapter.getAllItems()[pos])
            }
        }
        fetchTopAnime()
    }

    /**
     * Fetches the top anime from the use case and emits the state to the UI.
     * @param isHardRefresh If true, it forces a refresh of the data.
     */
    fun fetchTopAnime(isHardRefresh: Boolean = false) {
        viewModelScope.launch {
            fetchTopAnimeUseCase(isHardRefresh).collect { state ->
                _uiEvents.emit(state)
            }
        }
    }


    /**
     * Handles the response from the anime API call.
     * It parses the JSON element and updates the adapter with the new data.
     * @param jsonElement The JSON element containing the anime data.
     */
    fun handleTopAnimeResponse(jsonElement: JsonElement?) {
        isErrorShow.set(false)
        jsonElement?.let { json ->
            gsonProvider.getTopAnimeResponse(json).let {
                it.data?.let { list ->
                    adapter.addItems(list)
                }
            }
        }
    }
}