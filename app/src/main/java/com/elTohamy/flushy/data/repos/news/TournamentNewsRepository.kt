package com.elTohamy.flushy.data.repos.news

import com.elTohamy.flushy.data.remote.api.NewsApi
import com.elTohamy.flushy.data.remote.dto.news.FootballNews
import retrofit2.Response
import javax.inject.Inject

class TournamentNewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {
    suspend fun getTournamentNews(language: String, country: String, q: String): Response<FootballNews> =
        newsApi.getTournamentNews(language = language, country = country, q = q)
}