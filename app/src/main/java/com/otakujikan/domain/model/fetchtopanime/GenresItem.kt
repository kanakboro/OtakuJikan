package com.otakujikan.domain.model.fetchtopanime

import com.google.gson.annotations.SerializedName
import com.otakujikan.domain.model.base.AbstractModel

data class GenresItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
): AbstractModel()