package com.ranosan.challenge.boof.core.data.source.remote.response.movies

import com.google.gson.annotations.SerializedName

data class ImageResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("logos")
	val logos: List<LogosResponseItem>
)

data class LogosResponseItem(
	@field:SerializedName("file_path")
	val filePath: String? = null,
)
