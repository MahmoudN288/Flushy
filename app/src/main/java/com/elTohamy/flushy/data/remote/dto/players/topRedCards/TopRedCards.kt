package com.elTohamy.flushy.data.remote.dto.players.topRedCards

import com.google.gson.annotations.SerializedName

data class TopRedCards(

	@field:SerializedName("response")
	val response: List<TopRedResponseItem?>? = null,

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

data class Player(

    @field:SerializedName("injured")
	val injured: Boolean? = null,

    @field:SerializedName("firstname")
	val firstname: String? = null,

    @field:SerializedName("nationality")
	val nationality: String? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("birth")
	val birth: Birth? = null,

    @field:SerializedName("weight")
	val weight: String? = null,

    @field:SerializedName("photo")
	val photo: String? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("age")
	val age: Int? = null,

    @field:SerializedName("lastname")
	val lastname: String? = null,

    @field:SerializedName("height")
	val height: String? = null
)

data class Substitutes(

	@field:SerializedName("bench")
	val bench: Int? = null,

	@field:SerializedName("in")
	val inL: Int? = null,

	@field:SerializedName("out")
	val out: Int? = null
)

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Games(

	@field:SerializedName("number")
	val number: Any? = null,

	@field:SerializedName("minutes")
	val minutes: Int? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("appearences")
	val appearences: Int? = null,

	@field:SerializedName("position")
	val position: String? = null,

	@field:SerializedName("captain")
	val captain: Boolean? = null,

	@field:SerializedName("lineups")
	val lineups: Int? = null
)

data class Goals(

	@field:SerializedName("conceded")
	val conceded: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("saves")
	val saves: Any? = null,

	@field:SerializedName("assists")
	val assists: Int? = null
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

data class Shots(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("on")
	val on: Int? = null
)

data class Penalty(

	@field:SerializedName("saved")
	val saved: Any? = null,

	@field:SerializedName("scored")
	val scored: Int? = null,

	@field:SerializedName("missed")
	val missed: Int? = null,

	@field:SerializedName("won")
	val won: Any? = null,

	@field:SerializedName("commited")
	val commited: Any? = null
)

data class StatisticsItem(

    @field:SerializedName("fouls")
	val fouls: Fouls? = null,

    @field:SerializedName("cards")
	val cards: Cards? = null,

    @field:SerializedName("dribbles")
	val dribbles: Dribbles? = null,

    @field:SerializedName("substitutes")
	val substitutes: Substitutes? = null,

    @field:SerializedName("penalty")
	val penalty: Penalty? = null,

    @field:SerializedName("league")
	val league: League? = null,

    @field:SerializedName("team")
	val team: Team? = null,

    @field:SerializedName("duels")
	val duels: Duels? = null,

    @field:SerializedName("passes")
	val passes: Passes? = null,

    @field:SerializedName("games")
	val games: Games? = null,

    @field:SerializedName("tackles")
	val tackles: Tackles? = null,

    @field:SerializedName("shots")
	val shots: Shots? = null,

    @field:SerializedName("goals")
	val goals: Goals? = null
)

data class Passes(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("accuracy")
	val accuracy: Int? = null,

	@field:SerializedName("key")
	val key: Int? = null
)

data class Tackles(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("blocks")
	val blocks: Any? = null,

	@field:SerializedName("interceptions")
	val interceptions: Int? = null
)

data class Parameters(

	@field:SerializedName("league") //Required
	val league: Int? = null, //league=id

	@field:SerializedName("season") //Required
	val season: Int? = null //ex. season=2023
)

data class TopRedResponseItem(

    @field:SerializedName("player")
	val player: Player? = null,

    @field:SerializedName("statistics")
	val statistics: List<StatisticsItem?>? = null
)

data class Fouls(

	@field:SerializedName("committed")
	val committed: Int? = null,

	@field:SerializedName("drawn")
	val drawn: Int? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Birth(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("place")
	val place: String? = null
)

data class Cards(

	@field:SerializedName("red")
	val red: Int? = null,

	@field:SerializedName("yellowred")
	val yellowred: Int? = null,

	@field:SerializedName("yellow")
	val yellow: Int? = null
)
