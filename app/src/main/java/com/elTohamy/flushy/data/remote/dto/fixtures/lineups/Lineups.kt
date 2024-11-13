package com.elTohamy.flushy.data.remote.dto.fixtures.lineups

import com.google.gson.annotations.SerializedName

data class Lineups(

	@field:SerializedName("response")
	val response: List<LineupsResponseItem?>? = null,

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

data class LineupsResponseItem(

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

data class SubstitutesItem(

	@field:SerializedName("player")
	val player: SubstitutePlayer? = null
)

data class SubstitutePlayer(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("pos")
	val pos: String? = null,

	@field:SerializedName("grid")
	val grid: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class StartXIItem(

	@field:SerializedName("player")
	val player: StartingPlayer? = null
)

data class StartingPlayer(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("pos")
	val pos: String? = null,

	@field:SerializedName("grid")
	val grid: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("colors")
	val colors: Any? = null
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

	@field:SerializedName("fixture")
	val fixture: Int? = null, //fixture=id

	@field:SerializedName("team")
	val team: Int? = null, //team=id

	@field:SerializedName("player")
	val player: Int? = null, //player=id

	@field:SerializedName("type")
	val type: String? = null, //type=id
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)