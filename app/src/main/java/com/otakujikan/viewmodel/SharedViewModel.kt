package com.otakujikan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otakujikan.domain.model.fetchtopanime.DataItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _animeData = MutableSharedFlow<DataItem?>(replay = 1)
    val animeData: SharedFlow<DataItem?> = _animeData.asSharedFlow()

    /**
     * Sets the anime data to be shared across fragments.
     * This method is used to pass the selected anime data from one fragment to another.
     * @param dataItem The DataItem object containing the anime details.
     */
    fun setAnimeData(dataItem: DataItem) {
        viewModelScope.launch {
            _animeData.emit(dataItem)
        }
    }

}