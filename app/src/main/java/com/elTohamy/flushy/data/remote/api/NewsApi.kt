package com.elTohamy.flushy.data.remote.api

import com.elTohamy.flushy.BuildConfig
import com.elTohamy.flushy.data.remote.dto.news.FootballNews
import com.elTohamy.flushy.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val NEWS_API = BuildConfig.NEWS_API_KEY

interface NewsApi {
    @GET(Constants.NEWS_BASE_URL)
    suspend fun getTeamNews(
        @Query(Constants.NEWS_LANGUAGE) language: String,
        @Query(Constants.NEWS_QUERY) country: String,
        @Query(Constants.NEWS_QUERY) q1: String,
        @Query(Constants.NEWS_QUERY) q2: String,
        @Query(Constants.NEWS_API) apikey: String = NEWS_API
    ): Response<FootballNews>

    @GET(Constants.NEWS_BASE_URL)
    suspend fun getTournamentNews(
        @Query(Constants.NEWS_LANGUAGE) language: String,
        @Query(Constants.NEWS_CATEGORY) category: String = "sports",
        @Query(Constants.NEWS_QUERY) country: String,
        @Query(Constants.NEWS_QUERY) q: String,
        @Query(Constants.NEWS_API) apikey: String = NEWS_API
    ): Response<FootballNews>

    @GET(Constants.NEWS_BASE_URL)
    suspend fun getNews(
        @Query(Constants.NEWS_LANGUAGE) language: String,
        @Query(Constants.NEWS_CATEGORY) category: String = "sports",
        @Query(Constants.NEWS_QUERY) country: String,
        @Query(Constants.NEWS_QUERY) q: String,
        @Query(Constants.NEWS_API) apikey: String = NEWS_API
    ): Response<FootballNews>
}