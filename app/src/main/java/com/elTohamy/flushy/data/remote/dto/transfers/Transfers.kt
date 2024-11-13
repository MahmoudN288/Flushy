package com.elTohamy.flushy.data.remote.dto.transfers

import com.google.gson.annotations.SerializedName

data class Transfers(

	@field:SerializedName("response")
	val response: List<TransfersResponseItem?>? = null,

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

data class TransfersItem(

    @field:SerializedName("date")
	val date: String? = null,

    @field:SerializedName("teams")
	val teams: Teams? = null,

    @field:SerializedName("type")
	val type: String? = null
)

data class Player(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Teams(

    @field:SerializedName("in")
	val inL: In? = null,

    @field:SerializedName("out")
	val out: Out? = null
)

data class In(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class TransfersResponseItem(

    @field:SerializedName("transfers")
	val transfers: List<TransfersItem?>? = null,

    @field:SerializedName("update")
	val update: String? = null,

    @field:SerializedName("player")
	val player: Player? = null
)

data class Parameters(

	@field:SerializedName("team")
	val team: Int? = null, //team=id

	@field:SerializedName("player")
	val player: Int? = null //player=id
)

data class Out(

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
