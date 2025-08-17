package com.otakujikan.domain.model.fetchtopanime

import com.google.gson.annotations.SerializedName
import com.otakujikan.domain.model.base.AbstractModel

data class DataItem(

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("trailer")
    val trailer: Trailer? = null,

    @field:SerializedName("score")
    val score: Any? = null,

    @field:SerializedName("genres")
    val genres: List<GenresItem>? = null,

    @field:SerializedName("episodes")
    val episodes: Int? = null,

    @field:SerializedName("images")
    val images: Images? = null,

    @field:SerializedName("mal_id")
    val malId: Int? = null,

    @field:SerializedName("synopsis")
    val synopsis: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

) : AbstractModel()