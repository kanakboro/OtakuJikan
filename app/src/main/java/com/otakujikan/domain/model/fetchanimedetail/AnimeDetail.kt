package com.otakujikan.domain.model.fetchanimedetail

import com.google.gson.annotations.SerializedName
import com.otakujikan.domain.model.fetchtopanime.DataItem

data class AnimeDetail(
    @field:SerializedName("data")
    val data: DataItem? = null
)
