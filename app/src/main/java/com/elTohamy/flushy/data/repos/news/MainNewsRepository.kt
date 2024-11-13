package com.elTohamy.flushy.data.repos.news

import com.elTohamy.flushy.data.remote.api.NewsApi
import com.elTohamy.flushy.data.remote.dto.news.FootballNews
import retrofit2.Response
import javax.inject.Inject

class MainNewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {
    suspend fun getNews(language: String, country: String, q: String): Response<FootballNews> =
        newsApi.getNews(language = language, country = country, q = q)
}