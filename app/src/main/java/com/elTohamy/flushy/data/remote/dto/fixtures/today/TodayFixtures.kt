package com.elTohamy.flushy.data.remote.dto.fixtures.today

import com.google.gson.annotations.SerializedName

data class TodayFixtures(

	@field:SerializedName("response")
	val response: List<TodayGamesResponseItem?>? = null,

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

data class Penalty(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Parameters(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("league")
	val league: String? = null,

	@field:SerializedName("season")
	val season: String? = null
)

data class Teams(

	@field:SerializedName("away")
	val away: Away? = null,

	@field:SerializedName("home")
	val home: Home? = null
)

data class TodayGamesResponseItem(

	@field:SerializedName("fixture")
	val fixture: Fixture? = null,

	@field:SerializedName("score")
	val score: Score? = null,

	@field:SerializedName("teams")
	val teams: Teams? = null,

	@field:SerializedName("league")
	val league: League? = null,

	@field:SerializedName("goals")
	val goals: Goals? = null
)

data class Periods(

	@field:SerializedName("first")
	val first: Int? = null,

	@field:SerializedName("second")
	val second: Int? = null
)

data class Fulltime(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Fixture(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("venue")
	val venue: Venue? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("periods")
	val periods: Periods? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("referee")
	val referee: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: Int? = null,

	@field:SerializedName("status")
	val status: Status? = null
)

data class Away(

	@field:SerializedName("winner")
	val winner: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Home(

	@field:SerializedName("winner")
	val winner: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Venue(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Extratime(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Goals(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Status(

	@field:SerializedName("elapsed")
	val elapsed: Int? = null,

	@field:SerializedName("short")
	val jsonMemberShort: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: String? = null
)

data class League(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("flag")
	val flag: String? = null,

	@field:SerializedName("round")
	val round: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("season")
	val season: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Score(

	@field:SerializedName("halftime")
	val halftime: Halftime? = null,

	@field:SerializedName("penalty")
	val penalty: Penalty? = null,

	@field:SerializedName("fulltime")
	val fulltime: Fulltime? = null,

	@field:SerializedName("extratime")
	val extratime: Extratime? = null
)

data class Halftime(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)
