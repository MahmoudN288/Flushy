package com.elTohamy.flushy.data.remote.dto.fixtures.rounds

import com.google.gson.annotations.SerializedName

data class Rounds(

    @field:SerializedName("response")
	val response: List<String?>? = null,

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

data class Parameters(

	@field:SerializedName("league") //Required
	val league: Int? = null, //league=id

	@field:SerializedName("season") //Required
	val season: String? = null, //ex. season=2023

	@field:SerializedName("current")
	val current: Boolean? = null //current=true / current=false
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)
