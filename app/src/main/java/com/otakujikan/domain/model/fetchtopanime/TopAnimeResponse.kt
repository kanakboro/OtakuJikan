package com.otakujikan.domain.model.fetchtopanime

import com.google.gson.annotations.SerializedName

data class TopAnimeResponse(

    @field:SerializedName("data")
    val data: List<DataItem>? = null
)