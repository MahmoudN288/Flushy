package com.elTohamy.flushy.components.searchComponents.searchComponents.sharedPreferences

import android.content.Context
import android.content.SharedPreferences

class FavouriteLazyColumnSharedPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("LazyFavPrefs", Context.MODE_PRIVATE)

    fun setIndex(key: String, index: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, index)
        editor.apply()
    }

    fun getIndex(key: String, index: Int): Int {
        return sharedPreferences.getInt(key, index) ?: index
    }
}