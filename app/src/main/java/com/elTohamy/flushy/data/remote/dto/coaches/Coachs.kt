package com.elTohamy.flushy.data.remote.dto.coaches

import com.google.gson.annotations.SerializedName

data class Coachs(

	@field:SerializedName("response")
	val response: List<CoachsResponse?>? = null,

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

data class CoachsResponse(

    @field:SerializedName("firstname")
	val firstname: String? = null,

    @field:SerializedName("career")
	val career: List<CareerItem>? = null,

    @field:SerializedName("nationality")
	val nationality: String? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("birth")
	val birth: Birth? = null,

    @field:SerializedName("weight")
	val weight: String? = null,

    @field:SerializedName("photo")
	val photo: String? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("team")
	val team: Team? = null,

    @field:SerializedName("age")
	val age: Int? = null,

    @field:SerializedName("lastname")
	val lastname: String? = null,

    @field:SerializedName("height")
	val height: String? = null
)

data class Birth(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("place")
	val place: String? = null
)

data class CareerItem(

	@field:SerializedName("start")
	val start: String? = null,

	@field:SerializedName("end")
	val end: String? = null,

	@field:SerializedName("team")
	val team: Team? = null
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

data class Parameters(

	@field:SerializedName("coach")
	val coach: Int? = null, //coach=id

	@field:SerializedName("team")
	val team: Int? = null, //team=id

	@field:SerializedName("search")
	val search: String? = null // >3 characters with coach name ex. search=Car -> Carlo Ancelotti
)