package com.elTohamy.flushy.data.remote.dto.fixtures.byId

import com.google.gson.annotations.SerializedName

data class MatchByIdResponse(

	@field:SerializedName("response")
	val response: List<MatchByIdResponseItem?>? = null,

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

data class MatchByIdResponseItem(

	@field:SerializedName("fixture")
	val fixture: Fixture? = null,

	@field:SerializedName("score")
	val score: Score? = null,

	@field:SerializedName("teams")
	val teams: Teams? = null,

	@field:SerializedName("players")
	val players: List<MainPlayers?>? = null,

	@field:SerializedName("league")
	val league: League? = null,

	@field:SerializedName("events")
	val events: List<EventsItem?>? = null,

	@field:SerializedName("goals")
	val goals: TotalGoals? = null,

	@field:SerializedName("lineups")
	val lineups: List<LineupsItem?>? = null,

	@field:SerializedName("statistics")
	val statistics: List<TeamStatisticsItem?>? = null
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

data class Periods(

	@field:SerializedName("first")
	val first: Int? = null,

	@field:SerializedName("second")
	val second: Int? = null
)

data class Status(

	@field:SerializedName("elapsed")
	val elapsed: Int? = null,

	@field:SerializedName("short")
	val jsonMemberShort: String? = null,

	@field:SerializedName("long")
	val jsonMemberLong: String? = null
)

data class Score(

	@field:SerializedName("halftime")
	val halftime: Halftime? = null,

	@field:SerializedName("penalty")
	val penalty: FinalPenalty? = null,

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

data class FinalPenalty(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Fulltime(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Extratime(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Teams(

	@field:SerializedName("away")
	val away: Away? = null,

	@field:SerializedName("home")
	val home: Home? = null
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

data class MainPlayers(

	@field:SerializedName("players")
	val players: List<MatchPlayerItem?>? = null,

	@field:SerializedName("team")
	val team: PlayerTeam? = null
)

data class MatchPlayerItem(

	@field:SerializedName("player")
	val player: StatsNestedPlayer? = null,

	@field:SerializedName("statistics")
	val statistics: List<PlayerStatisticsItem?>? = null
)

data class StatsNestedPlayer(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null
)

data class PlayerStatisticsItem(

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
	val offsides: Int? = null,

	@field:SerializedName("goals")
	val goals: Goals? = null
)

data class PlayerTeam(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("update")
	val update: String? = null
)

data class Fouls(

	@field:SerializedName("committed")
	val committed: Int? = null,

	@field:SerializedName("drawn")
	val drawn: Int? = null
)

data class Cards(

	@field:SerializedName("red")
	val red: Int? = null,

	@field:SerializedName("yellow")
	val yellow: Int? = null
)

data class Passes(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("accuracy")
	val accuracy: String? = null,

	@field:SerializedName("key")
	val key: Int? = null
)

data class Dribbles(

	@field:SerializedName("success")
	val success: Int? = null,

	@field:SerializedName("past")
	val past: Int? = null,

	@field:SerializedName("attempts")
	val attempts: Int? = null
)

data class Penalty(

	@field:SerializedName("saved")
	val saved: Int? = null,

	@field:SerializedName("scored")
	val scored: Int? = null,

	@field:SerializedName("missed")
	val missed: Int? = null,

	@field:SerializedName("won")
	val won: Int? = null,

	@field:SerializedName("commited")
	val commited: Int? = null
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
	val total: Int? = null,

	@field:SerializedName("blocks")
	val blocks: Int? = null,

	@field:SerializedName("interceptions")
	val interceptions: Int? = null
)

data class Shots(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("on")
	val on: Int? = null
)

data class Duels(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("won")
	val won: Int? = null
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

data class EventsItem(

	@field:SerializedName("comments")
	val comments: String? = null,

	@field:SerializedName("assist")
	val assist: Assist? = null,

	@field:SerializedName("time")
	val time: Time? = null,

	@field:SerializedName("team")
	val team: EventTeam? = null,

	@field:SerializedName("detail")
	val detail: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("player")
	val player: EventPlayer? = null
)

data class Assist(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Time(

	@field:SerializedName("elapsed")
	val elapsed: Int? = null,

	@field:SerializedName("extra")
	val extra: Int? = null
)

data class EventTeam(
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("name")
	val name: String? = null,
	@field:SerializedName("logo")
	val logo: String? = null
)

data class EventPlayer(
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("name")
	val name: String? = null
)

data class TotalGoals(

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class LineupsItem(

	@field:SerializedName("substitutes")
	val substitutes: List<SubstitutesItem?>? = null,

	@field:SerializedName("startXI")
	val startXI: List<StartXIItem?>? = null,

	@field:SerializedName("team")
	val team: LineupsTeam? = null,

	@field:SerializedName("formation")
	val formation: String? = null,

	@field:SerializedName("coach")
	val coach: Coach? = null
)

data class SubstitutesItem(

	@field:SerializedName("player")
	val player: SubstitutePlayer? = null
)

data class SubstitutePlayer(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("pos")
	val pos: String? = null,

	@field:SerializedName("grid")
	val grid: String? = null
)

data class StartXIItem(

	@field:SerializedName("player")
	val player: LineupsPlayer? = null
)

data class LineupsPlayer(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("pos")
	val pos: String? = null,

	@field:SerializedName("grid")
	val grid: String? = null
)

data class LineupsTeam(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("colors")
	val colors: Colors? = null
)

data class Colors(

	@field:SerializedName("goalkeeper")
	val goalkeeper: Goalkeeper? = null,

	@field:SerializedName("player")
	val player: ColorsLineupsPlayer? = null
)

data class Goalkeeper(

	@field:SerializedName("border")
	val border: String? = null,

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("primary")
	val primary: String? = null
)

data class ColorsLineupsPlayer(

	@field:SerializedName("border")
	val border: String? = null,

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("primary")
	val primary: String? = null
)

data class Coach(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class TeamStatisticsItem(

	@field:SerializedName("team")
	val team: StatisticsTeam? = null,

	@field:SerializedName("statistics")
	val statistics: List<TeamStatsItem?>? = null
)

data class StatisticsTeam(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null
)

data class TeamStatsItem(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("value")
	val value: Any? = null
)

data class Parameters(

	@field:SerializedName("id")
	val id: String? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)