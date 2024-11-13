package com.elTohamy.flushy.data.local.news.repository

import com.elTohamy.flushy.data.local.news.SavedNews
import com.elTohamy.flushy.data.local.news.dao.SavedNewsDao
import javax.inject.Inject

class SavedNewsRepositoryImplementation @Inject constructor(
    private val savedNewsDao: SavedNewsDao
): SavedNewsRepository {
    override suspend fun getAllSavedNews(): List<SavedNews> = savedNewsDao.getAllSavedNews()

    override suspend fun getNewsByTitle(title: String): SavedNews = savedNewsDao.getNewsByTitle(title)

    override suspend fun addOneSavedNews(savedNews: SavedNews) = savedNewsDao.addOneSavedNews(savedNews)

    override suspend fun deleteOneSavedNews(savedNews: SavedNews) = savedNewsDao.deleteOneSavedNews(savedNews)

    override fun isNewsSaved(title: String): Boolean = savedNewsDao.isNewsSaved(title)
}