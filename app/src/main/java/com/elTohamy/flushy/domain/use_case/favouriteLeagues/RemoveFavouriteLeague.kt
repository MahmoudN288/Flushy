package com.elTohamy.flushy.domain.use_case.favouriteLeagues

import com.elTohamy.flushy.domain.repository.FavouriteLeaguesRepository
import javax.inject.Inject

class RemoveFavouriteLeague @Inject constructor(private val favouriteLeaguesRepository: FavouriteLeaguesRepository) {
    operator fun invoke(id: Int, userId: String) = favouriteLeaguesRepository.removeFavouriteLeague(id, userId)
}