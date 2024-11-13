package com.elTohamy.flushy.data.local.leagues.repository

import com.elTohamy.flushy.data.local.leagues.FavouriteLeagues
import com.elTohamy.flushy.data.local.leagues.dao.FavouriteLeaguesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouriteLeaguesRepositoryImplementation @Inject constructor(
    private val favouriteLeaguesDao: FavouriteLeaguesDao
): FavouriteLeaguesRepository {
    override suspend fun getAllLeagues(): Flow<List<FavouriteLeagues>> = favouriteLeaguesDao.getAllLeagues()

    override suspend fun getLeague(id: Int): FavouriteLeagues = favouriteLeaguesDao.getLeague(id)

    override suspend fun insertLeague(favouriteLeagues: FavouriteLeagues) = favouriteLeaguesDao.insertLeague(favouriteLeagues)

    override suspend fun updateLeague(favouriteLeagues: FavouriteLeagues) = favouriteLeaguesDao.updateLeague(favouriteLeagues)

    override suspend fun deleteLeague(favouriteLeagues: FavouriteLeagues) = favouriteLeaguesDao.deleteLeague(favouriteLeagues)

    override fun isRowExists(id: Int) = favouriteLeaguesDao.isRowExists(id)
}