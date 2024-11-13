package com.elTohamy.flushy.data.remote.dto.players.seasons

import com.google.gson.annotations.SerializedName

data class PlayerSeasons(

    @field:SerializedName("response")
	val response: List<Int?>? = null,

    @field:SerializedName("get")
	val get: String? = null,

    @field:SerializedName("paging")
	val paging: Paging? = null,

    @field:SerializedName("parameters")
	val parameters: Parameters? = null,

    @field:SerializedName("results")
	val results: Int? = null,

    @field:SerializedName("errors")
	val errors: List<Any?>? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Parameters(

	@field:SerializedName("player") //Required
	val player: Int? = null //player=id
)
