package com.elTohamy.flushy.domain.use_case.favouriteLeagues

data class UseCases(
    val getFavouriteLeagues: GetFavouriteLeagues,
    val addFavouriteLeagues: AddFavouriteLeagues,
    val removeFavouriteLeague: RemoveFavouriteLeague
)