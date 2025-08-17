package com.otakujikan.domain.provider.gsonprovider

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.otakujikan.domain.model.fetchanimedetail.AnimeDetail
import com.otakujikan.domain.model.fetchtopanime.DataItem
import com.otakujikan.domain.model.fetchtopanime.TopAnimeResponse
import javax.inject.Inject

class GsonProviderImpl @Inject constructor() : GsonProvider {

    /**
     * Converts a JsonElement to a TopAnimeResponse object.
     *
     * @param jsonElement The JSON element to convert.
     * @return The converted TopAnimeResponse object.
     */
    override fun getTopAnimeResponse(jsonElement: JsonElement): TopAnimeResponse {
        return Gson().fromJson(jsonElement, TopAnimeResponse::class.java)
    }

    /**
     * Converts a JsonElement to a DataItem object representing anime details.
     *
     * @param jsonElement The JSON element to convert.
     * @return The converted DataItem object, or null if the conversion fails.
     */
    override fun getAnimeDetail(jsonElement: JsonElement): DataItem? {
        return (Gson().fromJson(jsonElement, AnimeDetail::class.java)).data
    }
}