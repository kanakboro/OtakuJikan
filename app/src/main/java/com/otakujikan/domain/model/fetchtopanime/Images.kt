package com.otakujikan.domain.model.fetchtopanime

import com.google.gson.annotations.SerializedName

data class Images(

	@field:SerializedName("jpg")
	val jpg: Jpg? = null,

	@field:SerializedName("large_image_url")
	val largeImageUrl: String? = null,

	@field:SerializedName("maximum_image_url")
	val maximumImageUrl: String? = null
)