package com.elTohamy.flushy.data.remote.dto.injuries

import com.google.gson.annotations.SerializedName

data class Injuries(

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

data class League(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("flag")
	val flag: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("season")
	val season: Int? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Player(

	@field:SerializedName("reason")
	val reason: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class Parameters(

	@field:SerializedName("league") //Required
	val league: Int? = null, //league=id

	@field:SerializedName("season") //Required
	val season: Int? = null, //ex. season=2023

	@field:SerializedName("fixture")
	val fixture: Int? = null, //fixture=id

	@field:SerializedName("team")
	val team: Int? = null, //team=id

	@field:SerializedName("player")
	val player: Int? = null, //player=id

	@field:SerializedName("date")
	val date: String? = null, //date=YY-MM-DD

	@field:SerializedName("timezone")
	val timezone: String? = null //A valid timezone from the endpoint Timezone
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Fixture(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

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

    @field:SerializedName("team")
	val team: Team? = null,

    @field:SerializedName("player")
	val player: Player? = null
)
