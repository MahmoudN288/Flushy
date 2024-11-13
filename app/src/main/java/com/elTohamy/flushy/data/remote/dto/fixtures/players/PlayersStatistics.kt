package com.elTohamy.flushy.data.remote.dto.fixtures.players

import com.google.gson.annotations.SerializedName

data class PlayersStatistics(

	@field:SerializedName("response")
	val response: List<PlayersFixtureStatsResponseItem?>? = null,

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

data class Duels(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("won")
	val won: Int? = null
)

data class Fouls(

	@field:SerializedName("committed")
	val committed: Int? = null,

	@field:SerializedName("drawn")
	val drawn: Int? = null
)

data class Goals(

	@field:SerializedName("conceded")
	val conceded: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("saves")
	val saves: Int? = null,

	@field:SerializedName("assists")
	val assists: Int? = null
)

data class Tackles(

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("blocks")
	val blocks: Int? = null,

	@field:SerializedName("interceptions")
	val interceptions: Int? = null
)

data class Games(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("minutes")
	val minutes: Int? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("position")
	val position: String? = null,

	@field:SerializedName("captain")
	val captain: Boolean? = null,

	@field:SerializedName("substitute")
	val substitute: Boolean? = null
)

data class StatisticsItem(

    @field:SerializedName("fouls")
	val fouls: Fouls? = null,

    @field:SerializedName("cards")
	val cards: Cards? = null,

    @field:SerializedName("passes")
	val passes: Passes? = null,

    @field:SerializedName("dribbles")
	val dribbles: Dribbles? = null,

    @field:SerializedName("penalty")
	val penalty: Penalty? = null,

    @field:SerializedName("games")
	val games: Games? = null,

    @field:SerializedName("tackles")
	val tackles: Tackles? = null,

    @field:SerializedName("shots")
	val shots: Shots? = null,

    @field:SerializedName("duels")
	val duels: Duels? = null,

    @field:SerializedName("offsides")
	val offsides: Any? = null,

    @field:SerializedName("goals")
	val goals: Goals? = null
)

data class Parameters(

	@field:SerializedName("fixture") //Required
	val fixture: Int? = null, //fixture=id

	@field:SerializedName("team")
	val team: Int? = null //team=id
)

data class Cards(

	@field:SerializedName("red")
	val red: Int? = null,

	@field:SerializedName("yellow")
	val yellow: Int? = null
)

data class PlayersFixtureStatsResponseItem(

    @field:SerializedName("players")
	val players: List<PlayersItem?>? = null,

    @field:SerializedName("team")
	val team: Team? = null
)

data class Passes(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("accuracy")
	val accuracy: String? = null,

	@field:SerializedName("key")
	val key: Int? = null
)

data class Penalty(

	@field:SerializedName("saved")
	val saved: Int? = null,

	@field:SerializedName("scored")
	val scored: Int? = null,

	@field:SerializedName("missed")
	val missed: Int? = null,

	@field:SerializedName("won")
	val won: Any? = null,

	@field:SerializedName("commited")
	val commited: Any? = null
)

data class Shots(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("on")
	val on: Int? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class PlayersItem(

    @field:SerializedName("player")
	val player: Player? = null,

    @field:SerializedName("statistics")
	val statistics: List<StatisticsItem?>? = null
)

data class Player(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("update")
	val update: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Dribbles(

	@field:SerializedName("success")
	val success: Int? = null,

	@field:SerializedName("past")
	val past: Any? = null,

	@field:SerializedName("attempts")
	val attempts: Int? = null
)
