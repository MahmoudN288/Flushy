package com.elTohamy.flushy.data.remote.dto.trophies

import com.google.gson.annotations.SerializedName

data class Trophies(

	@field:SerializedName("response")
	val response: List<TrophiesResponseItem?>? = null,

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

data class TrophiesResponseItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("league")
	val league: String? = null,

	@field:SerializedName("season")
	val season: String? = null,

	@field:SerializedName("place")
	val place: String? = null
)

data class Parameters(

	@field:SerializedName("player")
	val player: Int? = null, //player=id

	@field:SerializedName("coach")
	val coach: Int? = null //coach=id
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)
