package com.elTohamy.flushy.data.remote.dto.predictions

import com.google.gson.annotations.SerializedName

data class Predictions(

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
	val errors: List<Any?>? = null,

    @field:SerializedName("under_over")
	val underOver: String? = null,

    @field:SerializedName("winner")
	val winner: Winner? = null,

    @field:SerializedName("win_or_draw")
	val winOrDraw: Boolean? = null,

    @field:SerializedName("advice")
	val advice: String? = null,

    @field:SerializedName("percent")
	val percent: Percent? = null,

    @field:SerializedName("goals")
	val goals: Goals? = null
)

data class JsonMember91105(

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("percentage")
	val percentage: Any? = null
)

data class Red(

    @field:SerializedName("106-120")
	val jsonMember106120: JsonMember106120? = null,

    @field:SerializedName("46-60")
	val jsonMember4660: JsonMember4660? = null,

    @field:SerializedName("31-45")
	val jsonMember3145: JsonMember3145? = null,

    @field:SerializedName("76-90")
	val jsonMember7690: JsonMember7690? = null,

    @field:SerializedName("0-15")
	val jsonMember015: JsonMember015? = null,

    @field:SerializedName("91-105")
	val jsonMember91105: JsonMember91105? = null,

    @field:SerializedName("16-30")
	val jsonMember1630: JsonMember1630? = null,

    @field:SerializedName("61-75")
	val jsonMember6175: JsonMember6175? = null
)

data class Played(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Away(

    @field:SerializedName("last_5")
	val last5: Last5? = null,

    @field:SerializedName("league")
	val league: League? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("logo")
	val logo: String? = null,

    @field:SerializedName("id")
	val id: Int? = null
)

data class Against(

    @field:SerializedName("average")
	val average: String? = null,

    @field:SerializedName("total")
	val total: Int? = null,

    @field:SerializedName("minute")
	val minute: Minute? = null,

    @field:SerializedName("away")
	val away: Int? = null,

    @field:SerializedName("home")
	val home: Int? = null
)

data class Fixtures(

    @field:SerializedName("wins")
	val wins: Wins? = null,

    @field:SerializedName("loses")
	val loses: Loses? = null,

    @field:SerializedName("draws")
	val draws: Draws? = null,

    @field:SerializedName("played")
	val played: Played? = null
)

data class PoissonDistribution(

	@field:SerializedName("away")
	val away: String? = null,

	@field:SerializedName("home")
	val home: String? = null
)

data class JsonMember6175(

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("percentage")
	val percentage: Any? = null
)

data class Biggest(

    @field:SerializedName("wins")
	val wins: Wins? = null,

    @field:SerializedName("loses")
	val loses: Loses? = null,

    @field:SerializedName("streak")
	val streak: Streak? = null,

    @field:SerializedName("goals")
	val goals: Goals? = null
)

data class Home(

    @field:SerializedName("last_5")
	val last5: Last5? = null,

    @field:SerializedName("league")
	val league: League? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("logo")
	val logo: String? = null,

    @field:SerializedName("id")
	val id: Int? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Parameters(

	@field:SerializedName("fixture") //Required
	val fixture: Int? = null //fixture=id
)

data class FailedToScore(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Scored(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("percentage")
	val percentage: String? = null
)

data class JsonMember015(

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("percentage")
	val percentage: Any? = null
)

data class Teams(

    @field:SerializedName("away")
	val away: Away? = null,

    @field:SerializedName("home")
	val home: Home? = null
)

data class Yellow(

    @field:SerializedName("106-120")
	val jsonMember106120: JsonMember106120? = null,

    @field:SerializedName("46-60")
	val jsonMember4660: JsonMember4660? = null,

    @field:SerializedName("31-45")
	val jsonMember3145: JsonMember3145? = null,

    @field:SerializedName("76-90")
	val jsonMember7690: JsonMember7690? = null,

    @field:SerializedName("0-15")
	val jsonMember015: JsonMember015? = null,

    @field:SerializedName("91-105")
	val jsonMember91105: JsonMember91105? = null,

    @field:SerializedName("16-30")
	val jsonMember1630: JsonMember1630? = null,

    @field:SerializedName("61-75")
	val jsonMember6175: JsonMember6175? = null
)

data class Missed(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("percentage")
	val percentage: String? = null
)

data class JsonMember3145(

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("percentage")
	val percentage: Any? = null
)

data class Winner(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Goals(

    @field:SerializedName("away")
	val away: String? = null,

    @field:SerializedName("home")
	val home: String? = null,

    @field:SerializedName("against")
	val against: Against? = null,

    @field:SerializedName("for")
	val jsonMemberFor: JsonMemberFor? = null
)

data class CleanSheet(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class H2h(

	@field:SerializedName("away")
	val away: String? = null,

	@field:SerializedName("home")
	val home: String? = null
)

data class Att(

	@field:SerializedName("away")
	val away: String? = null,

	@field:SerializedName("home")
	val home: String? = null
)

data class Comparison(

    @field:SerializedName("att")
	val att: Att? = null,

    @field:SerializedName("total")
	val total: Total? = null,

    @field:SerializedName("form")
	val form: Form? = null,

    @field:SerializedName("def")
	val def: Def? = null,

    @field:SerializedName("poisson_distribution")
	val poissonDistribution: PoissonDistribution? = null,

    @field:SerializedName("h2h")
	val h2h: H2h? = null,

    @field:SerializedName("goals")
	val goals: Goals? = null
)

data class Last5(

	@field:SerializedName("att")
	val att: String? = null,

	@field:SerializedName("form")
	val form: String? = null,

	@field:SerializedName("def")
	val def: String? = null,

	@field:SerializedName("played")
	val played: Int? = null,

	@field:SerializedName("goals")
	val goals: Goals? = null
)

data class Form(

	@field:SerializedName("away")
	val away: String? = null,

	@field:SerializedName("home")
	val home: String? = null
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

    @field:SerializedName("failed_to_score")
	val failedToScore: FailedToScore? = null,

    @field:SerializedName("cards")
	val cards: Cards? = null,

    @field:SerializedName("form")
	val form: String? = null,

    @field:SerializedName("biggest")
	val biggest: Biggest? = null,

    @field:SerializedName("clean_sheet")
	val cleanSheet: CleanSheet? = null,

    @field:SerializedName("penalty")
	val penalty: Penalty? = null,

    @field:SerializedName("fixtures")
	val fixtures: Fixtures? = null,

    @field:SerializedName("goals")
	val goals: Goals? = null,

    @field:SerializedName("lineups")
	val lineups: List<LineupsItem?>? = null
)

data class Draws(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("away")
	val away: Int? = null,

	@field:SerializedName("home")
	val home: Int? = null
)

data class Cards(

    @field:SerializedName("red")
	val red: Red? = null,

    @field:SerializedName("yellow")
	val yellow: Yellow? = null
)

data class Average(

	@field:SerializedName("total")
	val total: String? = null,

	@field:SerializedName("away")
	val away: String? = null,

	@field:SerializedName("home")
	val home: String? = null
)

data class JsonMember106120(

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("percentage")
	val percentage: Any? = null
)

data class JsonMember4660(

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("percentage")
	val percentage: Any? = null
)

data class JsonMember1630(

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("percentage")
	val percentage: Any? = null
)

data class LineupsItem(

	@field:SerializedName("formation")
	val formation: String? = null,

	@field:SerializedName("played")
	val played: Int? = null
)

data class Percent(

	@field:SerializedName("away")
	val away: String? = null,

	@field:SerializedName("draw")
	val draw: String? = null,

	@field:SerializedName("home")
	val home: String? = null
)

data class Wins(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("away")
	val away: String? = null,

	@field:SerializedName("home")
	val home: String? = null
)

data class Loses(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("away")
	val away: Any? = null,

	@field:SerializedName("home")
	val home: Any? = null
)

data class Def(

	@field:SerializedName("away")
	val away: String? = null,

	@field:SerializedName("home")
	val home: String? = null
)

data class JsonMember7690(

	@field:SerializedName("total")
	val total: Any? = null,

	@field:SerializedName("percentage")
	val percentage: Any? = null
)

data class Minute(

    @field:SerializedName("106-120")
	val jsonMember106120: JsonMember106120? = null,

    @field:SerializedName("46-60")
	val jsonMember4660: JsonMember4660? = null,

    @field:SerializedName("31-45")
	val jsonMember3145: JsonMember3145? = null,

    @field:SerializedName("76-90")
	val jsonMember7690: JsonMember7690? = null,

    @field:SerializedName("0-15")
	val jsonMember015: JsonMember015? = null,

    @field:SerializedName("91-105")
	val jsonMember91105: JsonMember91105? = null,

    @field:SerializedName("16-30")
	val jsonMember1630: JsonMember1630? = null,

    @field:SerializedName("61-75")
	val jsonMember6175: JsonMember6175? = null
)

data class JsonMemberFor(

    @field:SerializedName("average")
	val average: String? = null,

    @field:SerializedName("total")
	val total: Int? = null,

    @field:SerializedName("minute")
	val minute: Minute? = null,

    @field:SerializedName("away")
	val away: Int? = null,

    @field:SerializedName("home")
	val home: Int? = null
)

data class ResponseItem(

    @field:SerializedName("comparison")
	val comparison: Comparison? = null,

    @field:SerializedName("teams")
	val teams: Teams? = null,

    @field:SerializedName("league")
	val league: League? = null,

    @field:SerializedName("predictions")
	val predictions: Predictions? = null,

    @field:SerializedName("h2h")
	val h2h: List<Any?>? = null
)

data class Total(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("away")
	val away: String? = null,

	@field:SerializedName("home")
	val home: String? = null
)

data class Streak(

	@field:SerializedName("wins")
	val wins: Int? = null,

	@field:SerializedName("loses")
	val loses: Int? = null,

	@field:SerializedName("draws")
	val draws: Int? = null
)

data class Penalty(

    @field:SerializedName("total")
	val total: Int? = null,

    @field:SerializedName("scored")
	val scored: Scored? = null,

    @field:SerializedName("missed")
	val missed: Missed? = null
)
