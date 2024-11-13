package com.elTohamy.flushy.data.remote.dto.standings

import com.google.gson.annotations.SerializedName

data class Standings(

    @field:SerializedName("response")
	val response: List<StandingsResponse?>? = null,

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

data class All(

	@field:SerializedName("lose")
	val lose: Int? = null,

	@field:SerializedName("draw")
	val draw: Int? = null,

	@field:SerializedName("played")
	val played: Int? = null,

	@field:SerializedName("win")
	val win: Int? = null,

	@field:SerializedName("goals")
	val goals: Goals? = null
)

data class StandingsItemItem(

    @field:SerializedName("all")
	val all: All? = null,

    @field:SerializedName("away")
	val away: Away? = null,

    @field:SerializedName("form")
	val form: String? = null,

    @field:SerializedName("rank")
	val rank: Int? = null,

    @field:SerializedName("description")
	val description: String? = null,

    @field:SerializedName("update")
	val update: String? = null,

    @field:SerializedName("team")
	val team: Team? = null,

    @field:SerializedName("goalsDiff")
	val goalsDiff: Int? = null,

    @field:SerializedName("points")
	val points: Int? = null,

    @field:SerializedName("group")
	val group: String? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("home")
	val home: Home? = null
)

data class League(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("flag")
	val flag: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("season")
	val season: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("standings")
	val standings: List<List<StandingsItemItem?>?>? = null
)

data class Goals(

	@field:SerializedName("against")
	val against: Int? = null,

	@field:SerializedName("for")
	val jsonMemberFor: Int? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Away(

	@field:SerializedName("lose")
	val lose: Int? = null,

	@field:SerializedName("draw")
	val draw: Int? = null,

	@field:SerializedName("played")
	val played: Int? = null,

	@field:SerializedName("win")
	val win: Int? = null,

	@field:SerializedName("goals")
	val goals: Goals? = null
)

data class StandingsResponse(

	@field:SerializedName("league")
	val league: League? = null
)

data class Parameters(

	@field:SerializedName("league")
	val league: Int? = null, //league=id

	@field:SerializedName("season") //Required
	val season: Int? = null, //.ex season=2023

	@field:SerializedName("team")
	val team: Int? = null //team=id
)

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Home(

	@field:SerializedName("lose")
	val lose: Int? = null,

	@field:SerializedName("draw")
	val draw: Int? = null,

	@field:SerializedName("played")
	val played: Int? = null,

	@field:SerializedName("win")
	val win: Int? = null,

	@field:SerializedName("goals")
	val goals: Goals? = null
)
