package com.elTohamy.flushy.domain.use_case.favouriteLeagues

import com.elTohamy.flushy.domain.repository.FavouriteLeaguesRepository
import javax.inject.Inject

class GetFavouriteLeagues @Inject constructor(private val favouriteLeaguesRepository: FavouriteLeaguesRepository) {
    operator fun invoke(userId: String) = favouriteLeaguesRepository.getFavouriteLeagues(userId)
}