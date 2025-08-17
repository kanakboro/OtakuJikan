package com.otakujikan.domain.provider.gsonprovider

import com.google.gson.JsonElement
import com.otakujikan.domain.model.fetchtopanime.DataItem
import com.otakujikan.domain.model.fetchtopanime.TopAnimeResponse

interface GsonProvider {

    fun getTopAnimeResponse(jsonElement: JsonElement): TopAnimeResponse

    fun getAnimeDetail(jsonElement: JsonElement): DataItem?

}