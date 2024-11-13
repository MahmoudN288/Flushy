package com.elTohamy.flushy.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elTohamy.flushy.data.local.leagues.FavouriteLeagues
import com.elTohamy.flushy.data.local.leagues.dao.FavouriteLeaguesDao
import com.elTohamy.flushy.data.local.news.SavedNews
import com.elTohamy.flushy.data.local.news.dao.SavedNewsDao

@Database(entities = [FavouriteLeagues::class, SavedNews::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class FlushyDatabase: RoomDatabase() {
    abstract fun leaguesDao(): FavouriteLeaguesDao
    abstract fun savedNewsDau(): SavedNewsDao

   companion object {
       const val DATABASE_NAME = "flushy_database"
       @Volatile
       private var INSTANCE: FlushyDatabase? = null

       fun getDatabase(context: Context): FlushyDatabase {
           if (INSTANCE == null) {
               INSTANCE = Room.databaseBuilder(
                   context, FlushyDatabase::class.java, name = "flushyDatabase"
               ).build()
           }
           return INSTANCE!!
       }
   }
}