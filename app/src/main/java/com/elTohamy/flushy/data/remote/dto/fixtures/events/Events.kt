package com.elTohamy.flushy.data.remote.dto.fixtures.events

import com.google.gson.annotations.SerializedName

data class Events(

    @field:SerializedName("response")
	val response: List<EventsResponse?>? = null,

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
	val extra: Any? = null
)

data class Parameters(

	@field:SerializedName("fixture") //Required
	val fixture: Int? = null, //fixture=id

	@field:SerializedName("team")
	val team: Int? = null, //team=id

	@field:SerializedName("player")
	val player: Int? = null, //player=id

	@field:SerializedName("type")
	val type: String? = null //.ex type=goal
)

data class Player(

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
	val id: Int? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class EventsResponse(

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
