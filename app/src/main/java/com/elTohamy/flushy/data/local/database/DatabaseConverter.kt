package com.elTohamy.flushy.data.local.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DatabaseConverter {
    private val json = Json

    @TypeConverter
    fun convertPicEntityListToString(picsEntity: List<StatisticsItem>): String =
        json.encodeToString(picsEntity)

    @TypeConverter
    fun convertStringToPicEntityList(string: String): List<StatisticsItem> =
        json.decodeFromString(string)
}