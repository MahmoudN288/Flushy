package com.elTohamy.flushy.data.remote.dto.venues

import com.google.gson.annotations.SerializedName

data class Venues(

    @field:SerializedName("response")
	val response: List<VenuesResponse?>? = null,

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

	@field:SerializedName("id")
	val id: Int? = null, //id=id

	@field:SerializedName("name")
	val name: String? = null, //ex. name=wembley

	@field:SerializedName("city")
	val city: String? = null, //ex. city=london

	@field:SerializedName("country")
	val country: String? = null, //ex. country=england

	@field:SerializedName("search")
	val search: String? = null //To be string >= 3 characters search=name / search=city / search=country
)

data class VenuesResponse(

	@field:SerializedName("country")
	val country: String? = null,

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

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)
