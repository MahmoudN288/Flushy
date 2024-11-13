package com.elTohamy.flushy.data.local.news

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_news")
data class SavedNews(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "source_name")
    val sourceName: String,

    @ColumnInfo(name = "source_url")
    val sourceUrl: String
)