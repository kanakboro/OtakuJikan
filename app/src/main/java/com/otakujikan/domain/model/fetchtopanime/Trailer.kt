package com.otakujikan.domain.model.fetchtopanime

import com.google.gson.annotations.SerializedName

data class Trailer(

	@field:SerializedName("images")
	val images: Images? = null,

	@field:SerializedName("embed_url")
	val embedUrl: String? = null,

	@field:SerializedName("youtube_id")
	val youtubeId: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)