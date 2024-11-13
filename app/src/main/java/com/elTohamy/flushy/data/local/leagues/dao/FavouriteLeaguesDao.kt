package com.elTohamy.flushy.data.local.leagues.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.elTohamy.flushy.data.local.leagues.FavouriteLeagues
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteLeaguesDao {

    @Query("SELECT * from favouriteLeagues")
    fun getAllLeagues(): Flow<List<FavouriteLeagues>>

    @Query("SELECT * from favouriteLeagues WHERE id = :id")
    fun getLeague(id: Int): FavouriteLeagues

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLeague(leagues: FavouriteLeagues)

    @Update
    fun updateLeague(leagues: FavouriteLeagues)

    @Delete
    fun deleteLeague(leagues: FavouriteLeagues)

    @Query("SELECT EXISTS(SELECT * FROM favouriteLeagues WHERE id = :id)")
    fun isRowExists(id: Int): Boolean
}