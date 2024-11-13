package com.elTohamy.flushy.data.repos.news

import com.elTohamy.flushy.data.remote.api.NewsApi
import com.elTohamy.flushy.data.remote.dto.news.FootballNews
import retrofit2.Response
import javax.inject.Inject

class TeamNewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {
    suspend fun getTeamNews(language: String, country: String, q1: String, q2: String): Response<FootballNews> =
        newsApi.getTeamNews(language = language, country = country, q1 = q1, q2 = q2)
}