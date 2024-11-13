package com.elTohamy.flushy.data.remote.dto.sidelined

import com.google.gson.annotations.SerializedName

data class Sidelined(

	@field:SerializedName("response")
	val response: List<SidelinedResponseItem?>? = null,

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

data class Parameters(

	@field:SerializedName("player")
	val player: Int? = null, //player=id

	@field:SerializedName("coach")
	val coach: Int? = null //coach=id
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class SidelinedResponseItem(

	@field:SerializedName("start")
	val start: String? = null,

	@field:SerializedName("end")
	val end: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)
