package com.elTohamy.flushy.data.local.news.repository

import com.elTohamy.flushy.data.local.news.SavedNews

interface SavedNewsRepository {
    suspend fun getAllSavedNews(): List<SavedNews>
    suspend fun getNewsByTitle(title: String): SavedNews
    suspend fun addOneSavedNews(savedNews: SavedNews)
    suspend fun deleteOneSavedNews(savedNews: SavedNews)
    fun isNewsSaved(title: String): Boolean
}