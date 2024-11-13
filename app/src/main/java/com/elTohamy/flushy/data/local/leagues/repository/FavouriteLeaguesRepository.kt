package com.elTohamy.flushy.data.local.leagues.repository

import com.elTohamy.flushy.data.local.leagues.FavouriteLeagues
import kotlinx.coroutines.flow.Flow

interface FavouriteLeaguesRepository {
    suspend fun getAllLeagues(): Flow<List<FavouriteLeagues>>
    suspend fun getLeague(id: Int): FavouriteLeagues
    suspend fun insertLeague(favouriteLeagues: FavouriteLeagues)
    suspend fun updateLeague(favouriteLeagues: FavouriteLeagues)
    suspend fun deleteLeague(favouriteLeagues: FavouriteLeagues)
    fun isRowExists(id: Int): Boolean
}