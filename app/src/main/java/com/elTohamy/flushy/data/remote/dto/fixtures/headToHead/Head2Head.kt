package com.elTohamy.flushy.data.remote.dto.fixtures.headToHead

import com.google.gson.annotations.SerializedName

data class Head2Head(

    @field:SerializedName("response")
	val response: List<Head2HeadResponse?>? = null,

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

data class Goals(

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

data class Periods(

	@field:SerializedName("first")
	val first: Int? = null,

	@field:SerializedName("second")
	val second: Int? = null
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

data class Venue(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Penalty(

	@field:SerializedName("away")
	val away: Any? = null,

	@field:SerializedName("home")
	val home: Any? = null
)

data class Head2HeadResponse(

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

data class Parameters(

	@field:SerializedName("h2h") //Required
	val h2h: String? = null, //Ids of the teams max(2) h2h=id-id

	@field:SerializedName("date")
	val date: String? = null, //date=YY-MM-DD

	@field:SerializedName("league")
	val league: Int? = null, //league=id

	@field:SerializedName("season")
	val season: Int? = null, //ex. season=2023

	@field:SerializedName("last")
	val last: Int? = null,

	@field:SerializedName("next")
	val next: Int? = null,

	@field:SerializedName("from")
	val from: String? = null, //from=YY-MM-DD

	@field:SerializedName("to")
	val to: String? = null, //to=YY-MM-DD

	@field:SerializedName("status")
	val status: String? = null, //status=NS / status=NS-PST-FT

	@field:SerializedName("venue")
	val venue: Int? = null, //venue=id

	@field:SerializedName("timezone")
	val timezone: String? = null //A valid timezone from Timezone endpoint
)

data class Teams(

    @field:SerializedName("away")
	val away: Away? = null,

    @field:SerializedName("home")
	val home: Home? = null
)

data class Away(

	@field:SerializedName("winner")
	val winner: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Fulltime(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
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

data class Extratime(

	@field:SerializedName("away")
	val away: Any? = null,

	@field:SerializedName("home")
	val home: Any? = null
)

data class Home(

	@field:SerializedName("winner")
	val winner: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
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

data class Halftime(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)
