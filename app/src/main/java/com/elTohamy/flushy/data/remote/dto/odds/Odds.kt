package com.elTohamy.flushy.data.remote.dto.odds

import com.google.gson.annotations.SerializedName

data class Odds(

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

data class BetsItem(

    @field:SerializedName("values")
	val values: List<ValuesItem?>? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("id")
	val id: Int? = null
)

data class BookmakersItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("bets")
	val bets: List<BetsItem?>? = null
)

data class Parameters(

	@field:SerializedName("league")
	val league: Int? = null, //league=id

	@field:SerializedName("season")
	val season: Int? = null, //ex. season=2023

	@field:SerializedName("fixture")
	val fixture: Int? = null, //fixture=id

	@field:SerializedName("date")
	val date: String? = null, //date=YY-MM-DD

	@field:SerializedName("timezone")
	val timezone: String? = null, //A valid timezone from the endpoint Timezone

	@field:SerializedName("page")
	val page: Int? = null, //page=1 (Default)

	@field:SerializedName("bookmaker")
	val bookmaker: Int? = null, //bookmaker=id

	@field:SerializedName("bet")
	val bet: Int? = null, //bet=id
)

data class League(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("flag")
	val flag: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("logo")
	val logo: String? = null,

	@field:SerializedName("season")
	val season: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class ResponseItem(

    @field:SerializedName("fixture")
	val fixture: Fixture? = null,

    @field:SerializedName("league")
	val league: League? = null,

    @field:SerializedName("bookmakers")
	val bookmakers: List<BookmakersItem?>? = null,

    @field:SerializedName("update")
	val update: String? = null
)

data class Fixture(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("timestamp")
	val timestamp: Int? = null
)

data class ValuesItem(

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("odd")
	val odd: String? = null
)

data class Paging(

	@field:SerializedName("current")
	val current: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null
)
