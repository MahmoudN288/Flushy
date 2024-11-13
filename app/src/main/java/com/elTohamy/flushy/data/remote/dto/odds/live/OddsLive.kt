package com.elTohamy.flushy.data.remote.dto.odds.live

import com.google.gson.annotations.SerializedName

data class OddsLive(

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

data class OddsItem(

    @field:SerializedName("values")
	val values: List<ValuesItem?>? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("id")
	val id: Int? = null
)

data class ResponseItem(

    @field:SerializedName("fixture")
	val fixture: Fixture? = null,

    @field:SerializedName("teams")
	val teams: Teams? = null,

    @field:SerializedName("league")
	val league: League? = null,

    @field:SerializedName("odds")
	val odds: List<OddsItem?>? = null,

    @field:SerializedName("update")
	val update: String? = null,

    @field:SerializedName("status")
	val status: Status? = null
)

data class Fixture(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class Teams(

    @field:SerializedName("away")
	val away: Away? = null,

    @field:SerializedName("home")
	val home: Home? = null
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

data class ValuesItem(

	@field:SerializedName("handicap")
	val handicap: Any? = null,

	@field:SerializedName("main")
	val main: Any? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("odd")
	val odd: String? = null,

	@field:SerializedName("suspended")
	val suspended: Boolean? = null
)

data class Away(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("goals")
	val goals: Int? = null
)

data class Home(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("goals")
	val goals: Int? = null
)

data class Status(

	@field:SerializedName("stopped")
	val stopped: Boolean? = null,

	@field:SerializedName("blocked")
	val blocked: Boolean? = null,

	@field:SerializedName("finished")
	val finished: Boolean? = null,

	@field:SerializedName("elapsed")
	val elapsed: Int? = null,

	@field:SerializedName("seconds")
	val seconds: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: String? = null
)

data class Parameters(
	@field:SerializedName("fixture")
	val fixture: Int? = null, //fixture=id

	@field:SerializedName("league")
	val league: Int? = null, //league=id

	@field:SerializedName("bet")
	val bet: Int? = null //bet=id
)
