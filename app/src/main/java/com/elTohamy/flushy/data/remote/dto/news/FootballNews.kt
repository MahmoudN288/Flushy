package com.elTohamy.flushy.data.remote.dto.news

import com.google.gson.annotations.SerializedName

data class FootballNews(

	@field:SerializedName("totalArticles")
	val totalArticles: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null
)

data class Source(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class ArticlesItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
