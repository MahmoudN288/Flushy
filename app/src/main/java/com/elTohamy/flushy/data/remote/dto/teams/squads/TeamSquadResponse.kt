package com.elTohamy.flushy.data.remote.dto.teams.squads

import com.google.gson.annotations.SerializedName

data class TeamSquadResponse(

	@field:SerializedName("response")
	val response: List<TeamSquadResponseItem?>? = null,

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

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class PlayersItem(

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("position")
	val position: String? = null,

	@field:SerializedName("age")
	val age: Int? = null
)

data class Parameters(

	@field:SerializedName("team")
	val team: String? = null
)

data class TeamSquadResponseItem(

	@field:SerializedName("players")
	val players: List<PlayersItem?>? = null,

	@field:SerializedName("team")
	val team: Team? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)
