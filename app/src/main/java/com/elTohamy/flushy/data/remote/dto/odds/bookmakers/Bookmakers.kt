package com.elTohamy.flushy.data.remote.dto.odds.bookmakers

import com.google.gson.annotations.SerializedName

data class Bookmakers(

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
	val errors: List<Any?>? = null
)

data class ResponseItem(

	@field:SerializedName("name")
	val name: String? = null,

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
	@field:SerializedName("id")
	val id: Int? = null, //id=id (Id of Bookmaker)

	@field:SerializedName("search")
	val search: String? = null //search=xxx (=3 characters of the bookmaker)
)
