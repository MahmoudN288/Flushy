package com.elTohamy.flushy.domain.response

sealed class FavouriteLeaguesResponse<out T> {
    data object Loading: FavouriteLeaguesResponse<Nothing>()

    data class LeaguesSuccess<out T>(
        val data: T
    ): FavouriteLeaguesResponse<T>()

    data class LeaguesFailure(
        val e: Exception?
    ): FavouriteLeaguesResponse<Nothing>()
}