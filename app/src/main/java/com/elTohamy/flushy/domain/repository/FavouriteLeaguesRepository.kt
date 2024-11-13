package com.elTohamy.flushy.domain.repository

import com.elTohamy.flushy.domain.model.FavouriteLeagues
import com.elTohamy.flushy.domain.response.FavouriteLeaguesResponse
import kotlinx.coroutines.flow.Flow

typealias favouriteLeagues = List<FavouriteLeagues>
typealias AddFavouriteLeaguesResponse = FavouriteLeaguesResponse<Boolean>
typealias RemoveFavouriteLeagueResponse = FavouriteLeaguesResponse<Boolean>
typealias GetFavouriteLeaguesResponse = FavouriteLeaguesResponse<favouriteLeagues>

interface FavouriteLeaguesRepository {
    fun addFavouriteLeagues(
        id: Int, name: String, logo: String, season: Int, country: String, userId: String
    ): AddFavouriteLeaguesResponse
    fun removeFavouriteLeague(id: Int, userId: String): RemoveFavouriteLeagueResponse
    fun getFavouriteLeagues(userId: String): Flow<GetFavouriteLeaguesResponse>
}