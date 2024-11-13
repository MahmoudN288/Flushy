package com.elTohamy.flushy.data.remote.dto.fixtures.stats

import com.google.gson.annotations.SerializedName

data class Statistics(

    @field:SerializedName("response")
	val response: List<StatisticsResponse?>? = null,

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

data class StatisticsItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("value")
	val value: Int? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Parameters(

	@field:SerializedName("fixture") //Required
	val fixture: Int? = null, //fixture=id

	@field:SerializedName("team")
	val team: Int? = null, //team=id

	@field:SerializedName("type")
	val type: String? = null //ex. type=corner kicks
)

data class StatisticsResponse(

    @field:SerializedName("team")
	val team: Team? = null,

    @field:SerializedName("statistics")
	val statistics: List<StatisticsItem?>? = null
)

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
