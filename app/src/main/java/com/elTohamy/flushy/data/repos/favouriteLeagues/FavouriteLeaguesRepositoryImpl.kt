package com.elTohamy.flushy.data.repos.favouriteLeagues

import com.elTohamy.flushy.domain.model.FavouriteLeagues
import com.elTohamy.flushy.domain.repository.AddFavouriteLeaguesResponse
import com.elTohamy.flushy.domain.repository.FavouriteLeaguesRepository
import com.elTohamy.flushy.domain.response.FavouriteLeaguesResponse
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FavouriteLeaguesRepositoryImpl @Inject constructor(
    private val favouriteLeaguesReference: CollectionReference
): FavouriteLeaguesRepository {
    override fun addFavouriteLeagues(
        id: Int,
        name: String,
        logo: String,
        season: Int,
        country: String,
        userId: String
    ): AddFavouriteLeaguesResponse = try {
        val favouriteLeagues = FavouriteLeagues(
            id = id, name = name, logo = logo, season = season, country = country
        )
        favouriteLeaguesReference.document(userId).collection("leagues")
            .document(id.toString()).set(favouriteLeagues)
        FavouriteLeaguesResponse.LeaguesSuccess(true)
    } catch (e: Exception) {
        FavouriteLeaguesResponse.LeaguesFailure(e)
    }

    override fun removeFavouriteLeague(id: Int, userId: String) = try {
        favouriteLeaguesReference.document(userId).collection("leagues")
            .document(id.toString()).delete()
        FavouriteLeaguesResponse.LeaguesSuccess(true)
    } catch (e: Exception) {
        FavouriteLeaguesResponse.LeaguesFailure(e)
    }

    override fun getFavouriteLeagues(userId: String) = callbackFlow {
        val snapshotListener = favouriteLeaguesReference.document(userId)
            .collection("leagues").orderBy("id").addSnapshotListener { snapshot, error ->
                val favouriteLeaguesResponse = if (snapshot != null) {
                    val favouriteLeagues = snapshot.toObjects(FavouriteLeagues::class.java)
                    FavouriteLeaguesResponse.LeaguesSuccess(favouriteLeagues)
                } else {
                    FavouriteLeaguesResponse.LeaguesFailure(error)
                }
                trySend(favouriteLeaguesResponse)
            }
        awaitClose { snapshotListener.remove() }
    }
}