package com.elTohamy.flushy.presentation.search

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchedFilterDatastore(private val context: Context) {

    // to make sure there is only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("RadioState")
        val USER_INDEX_KEY = stringPreferencesKey("radio_state")
    }

    // to get the email
    val getIndex: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_INDEX_KEY] ?: ""
        }

    // to save the email
    suspend fun saveIndex(newIndex: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_INDEX_KEY] = newIndex
        }
    }
}