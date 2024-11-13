package com.elTohamy.flushy.data.remote.dto.fixtures

import com.google.gson.annotations.SerializedName

data class Fixtures(

	@field:SerializedName("response")
	val response: List<FixturesResponseItem?>? = null,

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

data class Dribbles(

	@field:SerializedName("success")
	val success: Int? = null,

	@field:SerializedName("past")
	val past: Any? = null,

	@field:SerializedName("attempts")
	val attempts: Int? = null
)

data class Halftime(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class EventsItem(

    @field:SerializedName("comments")
	val comments: Any? = null,

    @field:SerializedName("assist")
	val assist: Assist? = null,

    @field:SerializedName("time")
	val time: Time? = null,

    @field:SerializedName("team")
	val team: Team? = null,

    @field:SerializedName("detail")
	val detail: String? = null,

    @field:SerializedName("type")
	val type: String? = null,

    @field:SerializedName("player")
	val player: Player? = null
)

data class Fouls(

	@field:SerializedName("committed")
	val committed: Int? = null,

	@field:SerializedName("drawn")
	val drawn: Int? = null
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

data class Goals(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null,

	@field:SerializedName("conceded")
	val conceded: Int? = null,

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("saves")
	val saves: Int? = null,

	@field:SerializedName("assists")
	val assists: Any? = null
)

data class LineupsItem(

    @field:SerializedName("substitutes")
	val substitutes: List<SubstitutesItem?>? = null,

    @field:SerializedName("startXI")
	val startXI: List<StartXIItem?>? = null,

    @field:SerializedName("team")
	val team: Team? = null,

    @field:SerializedName("formation")
	val formation: String? = null,

    @field:SerializedName("coach")
	val coach: Coach? = null
)

data class FixturesResponseItem(

    @field:SerializedName("fixture")
	val fixture: Fixture? = null,

    @field:SerializedName("score")
	val score: Score? = null,

    @field:SerializedName("teams")
	val teams: Teams? = null,

    @field:SerializedName("players")
	val players: List<PlayersItem?>? = null,

    @field:SerializedName("league")
	val league: League? = null,

    @field:SerializedName("events")
	val events: List<EventsItem?>? = null,

    @field:SerializedName("goals")
	val goals: Goals? = null,

    @field:SerializedName("lineups")
	val lineups: List<LineupsItem?>? = null,

    @field:SerializedName("statistics")
	val statistics: List<StatisticsItem?>? = null
)

data class Penalty(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null,

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

data class Time(

	@field:SerializedName("elapsed")
	val elapsed: Int? = null,

	@field:SerializedName("extra")
	val extra: Any? = null
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
	val goals: Goals? = null,

    @field:SerializedName("team")
	val team: Team? = null,

    @field:SerializedName("statistics")
	val statistics: List<StatisticsItem?>? = null,

    @field:SerializedName("type")
	val type: String? = null,

    @field:SerializedName("value")
	val value: Int? = null
)

data class Fulltime(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Player(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("pos")
	val pos: String? = null,

	@field:SerializedName("grid")
	val grid: Any? = null
)

data class Teams(

    @field:SerializedName("away")
	val away: Away? = null,

    @field:SerializedName("home")
	val home: Home? = null
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

data class Status(

	@field:SerializedName("elapsed")
	val elapsed: Int? = null,

	@field:SerializedName("short")
	val jsonMemberShort: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: String? = null
)

data class PlayersItem(

    @field:SerializedName("players")
	val players: List<PlayersItem?>? = null,

    @field:SerializedName("team")
	val team: Team? = null,

    @field:SerializedName("player")
	val player: Player? = null,

    @field:SerializedName("statistics")
	val statistics: List<StatisticsItem?>? = null
)

data class StartXIItem(

	@field:SerializedName("player")
	val player: Player? = null
)

data class Passes(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("accuracy")
	val accuracy: String? = null,

	@field:SerializedName("key")
	val key: Int? = null
)

data class Shots(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("on")
	val on: Int? = null
)

data class Cards(

	@field:SerializedName("red")
	val red: Int? = null,

	@field:SerializedName("yellow")
	val yellow: Int? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Extratime(

	@field:SerializedName("away")
	val away: Any? = null,

	@field:SerializedName("home")
	val home: Any? = null
)

data class Duels(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("won")
	val won: Int? = null
)

data class Periods(

	@field:SerializedName("first")
	val first: Int? = null,

	@field:SerializedName("second")
	val second: Int? = null
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

data class Tackles(

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("blocks")
	val blocks: Int? = null,

	@field:SerializedName("interceptions")
	val interceptions: Int? = null
)

data class SubstitutesItem(

	@field:SerializedName("player")
	val player: Player? = null
)

data class Venue(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
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

data class Assist(

	@field:SerializedName("name")
	val name: Any? = null,

	@field:SerializedName("id")
	val id: Any? = null
)

data class Coach(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Parameters(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("ids")
	val ids: String? = null, //Maximum of 20 different ids in the form ids="id-id-id-..etc"

	@field:SerializedName("live")
	val live: String? = null, //All or several leagues ids in the form live="id-id-id-..etc"

	@field:SerializedName("date")
	val date: String? = null, //In the form of date=YY-MM-DD

	@field:SerializedName("league")
	val league: String? = null,

	@field:SerializedName("season")
	val season: String? = null,

	@field:SerializedName("team")
	val team: Int? = null, //In the form ex. id=12

	@field:SerializedName("from")
	val from: String? = null, //In the date format from=YY-MM-DD

	@field:SerializedName("to")
	val to: String? = null, //In the date format from=YY-MM-DD

	@field:SerializedName("round")
	val round: String? = null, //Round of fixture ex. round=22

	@field:SerializedName("status")
	val status: String? = null, //In the form of status=NS or status=NS-PST-FT  / One or more status short

	@field:SerializedName("venue")
	val venue: Int? = null, //venue=id

	@field:SerializedName("timezone")
	val timezone: String? = null //A valid timezone from timezone endpoint
)

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("update")
	val update: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("colors")
	val colors: Any? = null
)
