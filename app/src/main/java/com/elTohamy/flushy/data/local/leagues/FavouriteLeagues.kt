package com.elTohamy.flushy.data.local.leagues

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteLeagues")
data class FavouriteLeagues(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "logo")
    val logo: String,

    @ColumnInfo(name = "season")
    val season: Int,

    @ColumnInfo(name = "country")
    val country: String
)