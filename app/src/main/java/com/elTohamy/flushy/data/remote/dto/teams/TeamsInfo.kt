package com.elTohamy.flushy.data.remote.dto.teams

import com.google.gson.annotations.SerializedName

data class TeamsInfo(

	@field:SerializedName("response")
	val response: List<TeamsInfoResponse?>? = null,

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

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class Venue(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("surface")
	val surface: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("capacity")
	val capacity: Int? = null
)

data class Team(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("founded")
	val founded: Int? = null,

	@field:SerializedName("national")
	val national: Boolean? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class TeamsInfoResponse(

	@field:SerializedName("venue")
	val venue: Venue? = null,

	@field:SerializedName("team")
	val team: Team? = null
)

data class Parameters(

	@field:SerializedName("id")
	val id: Int? = null, //id=id

	@field:SerializedName("name")
	val name: String? = null, //ex. name=real madrid

	@field:SerializedName("league")
	val league: Int? = null, //league=id

	@field:SerializedName("season")
	val season: Int? = null, //season=YY-MM-DD

	@field:SerializedName("country")
	val country: String? = null, //ex. name=spain

	@field:SerializedName("code")
	val code: String? = null, //ex. code=ESP

	@field:SerializedName("venue")
	val venue: Int? = null, //venue=id

	@field:SerializedName("search")
	val search: String? = null  //search=name / search=country
)
