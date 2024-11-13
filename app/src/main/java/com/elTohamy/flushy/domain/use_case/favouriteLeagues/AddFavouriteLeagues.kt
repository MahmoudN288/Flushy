package com.elTohamy.flushy.domain.use_case.favouriteLeagues

import com.elTohamy.flushy.domain.repository.FavouriteLeaguesRepository
import javax.inject.Inject

class AddFavouriteLeagues @Inject constructor(private val favouriteLeaguesRepository: FavouriteLeaguesRepository) {
    operator fun invoke(
        id: Int, name: String, logo: String, season: Int, country: String, userId: String
    ) = favouriteLeaguesRepository.addFavouriteLeagues(id, name, logo, season, country, userId)
}