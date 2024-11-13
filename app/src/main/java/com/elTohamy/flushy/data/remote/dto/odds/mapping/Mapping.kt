package com.elTohamy.flushy.data.remote.dto.odds.mapping

import com.google.gson.annotations.SerializedName

data class Mapping(

    @field:SerializedName("response")
	val response: List<ResponseItem?>? = null,

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

data class Fixture(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("timestamp")
	val timestamp: Int? = null
)

data class ResponseItem(

    @field:SerializedName("fixture")
	val fixture: Fixture? = null,

    @field:SerializedName("league")
	val league: League? = null,

    @field:SerializedName("update")
	val update: String? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class League(

	@field:SerializedName("season")
	val season: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Parameters(

	@field:SerializedName("page")
	val page: Int? = null //ex. page=1 (Pagination)
)
