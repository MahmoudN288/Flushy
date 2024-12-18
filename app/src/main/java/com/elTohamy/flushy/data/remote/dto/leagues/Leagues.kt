package com.elTohamy.flushy.data.remote.dto.leagues

import com.google.gson.annotations.SerializedName

data class Leagues(

	@field:SerializedName("response")
	val response: List<LeaguesResponseItem?>? = null,

	@field:SerializedName("get")
	val get: String? = null,

	@field:SerializedName("paging")
	val paging: Paging? = null,

	@field:SerializedName("parameters")
	val parameters: List<Parameters?>? = null,

	@field:SerializedName("results")
	val results: Int? = null,

	@field:SerializedName("errors")
	val errors: List<Any?>? = null
)

data class LeaguesResponseItem(

	@field:SerializedName("country")
	val country: Country? = null,

	@field:SerializedName("seasons")
	val seasons: List<SeasonsItem?>? = null,

	@field:SerializedName("league")
	val league: League? = null
)

data class Country(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("flag")
	val flag: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class SeasonsItem(

	@field:SerializedName("coverage")
	val coverage: Coverage? = null,

	@field:SerializedName("current")
	val current: Boolean? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("start")
	val start: String? = null,

	@field:SerializedName("end")
	val end: String? = null
)

data class Coverage(

	@field:SerializedName("top_cards")
	val topCards: Boolean? = null,

	@field:SerializedName("injuries")
	val injuries: Boolean? = null,

	@field:SerializedName("players")
	val players: Boolean? = null,

	@field:SerializedName("odds")
	val odds: Boolean? = null,

	@field:SerializedName("top_assists")
	val topAssists: Boolean? = null,

	@field:SerializedName("standings")
	val standings: Boolean? = null,

	@field:SerializedName("top_scorers")
	val topScorers: Boolean? = null,

	@field:SerializedName("predictions")
	val predictions: Boolean? = null,

	@field:SerializedName("fixtures")
	val fixtures: Fixtures? = null
)

data class Fixtures(

	@field:SerializedName("statistics_fixtures")
	val statisticsFixtures: Boolean? = null,

	@field:SerializedName("statistics_players")
	val statisticsPlayers: Boolean? = null,

	@field:SerializedName("events")
	val events: Boolean? = null,

	@field:SerializedName("lineups")
	val lineups: Boolean? = null
)

data class League(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class Parameters(

	@field:SerializedName("id")
	val id: Int? = null, //id=id

	@field:SerializedName("name")
	val name: String? = null, //ex. name=la liga

	@field:SerializedName("country")
	val country: String? = null, //ex. country=spain

	@field:SerializedName("code")
	val code: String? = null, //ex. code=FR / code=SP

	@field:SerializedName("season")
	val season: Int? = null, //ex. season=2023

	@field:SerializedName("team")
	val team: Int? = null, //team=id

	@field:SerializedName("type")
	val type: String? = null, //ex. type=league / type=cup

	@field:SerializedName("current")
	val current: String? = null, //ex. current=true (Current Season)

	@field:SerializedName("search")
	val search: String? = null, //search=name / search=country

	@field:SerializedName("last")
	val last: Int? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)