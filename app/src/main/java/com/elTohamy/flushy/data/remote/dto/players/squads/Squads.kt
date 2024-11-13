package com.elTohamy.flushy.data.remote.dto.players.squads

import com.google.gson.annotations.SerializedName

data class Squads(

	@field:SerializedName("response")
	val response: List<SquadsResponseItem?>? = null,

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

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Team(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class SquadsResponseItem(

    @field:SerializedName("players")
	val players: List<PlayersItem?>? = null,

    @field:SerializedName("team")
	val team: Team? = null
)

data class Parameters(

	@field:SerializedName("player")
	val player: Int? = null, //player=id

	@field:SerializedName("team")
	val team: Int? = null, //team=id
)
