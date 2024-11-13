package com.elTohamy.flushy.data.local.news.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elTohamy.flushy.data.local.news.SavedNews

@Dao
interface SavedNewsDao {
    @Query("SELECT * FROM saved_news")
    fun getAllSavedNews(): List<SavedNews>

    @Query("SELECT * FROM saved_news WHERE title = :title")
    fun getNewsByTitle(title: String): SavedNews

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addOneSavedNews(savedNews: SavedNews)

    @Delete
    fun deleteOneSavedNews(savedNews: SavedNews)

    @Query("SELECT EXISTS(SELECT * FROM saved_news WHERE title = :title)")
    fun isNewsSaved(title: String): Boolean
}