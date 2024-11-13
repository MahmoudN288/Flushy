package com.elTohamy.flushy.presentation.bottomNavBar.allTournaments.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elTohamy.flushy.domain.network.NetworkStatus
import com.elTohamy.flushy.domain.repository.AddFavouriteLeaguesResponse
import com.elTohamy.flushy.domain.repository.GetFavouriteLeaguesResponse
import com.elTohamy.flushy.domain.repository.RemoveFavouriteLeagueResponse
import com.elTohamy.flushy.domain.response.FavouriteLeaguesResponse
import com.elTohamy.flushy.domain.use_case.favouriteLeagues.UseCases
import com.elTohamy.flushy.services.NetworkConnectivityService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaguesViewModel @Inject constructor(
    private val useCases: UseCases, networkConnectivityService: NetworkConnectivityService
): ViewModel() {
    var favouriteLeaguesResponse by mutableStateOf<GetFavouriteLeaguesResponse>(FavouriteLeaguesResponse.Loading)
        private set
    var addFavouriteLeagueResponse by mutableStateOf<AddFavouriteLeaguesResponse>(FavouriteLeaguesResponse.LeaguesSuccess(false))
        private set
    var removeFavouriteLeagueResponse by mutableStateOf<RemoveFavouriteLeagueResponse>(FavouriteLeaguesResponse.LeaguesSuccess(false))
        private set

    fun getFavouriteLeagues(userId: String) = viewModelScope.launch {
        useCases.getFavouriteLeagues(userId).collect { response ->
            favouriteLeaguesResponse = response
        }
    }

    fun addFavouriteLeague(
        id: Int, name: String, logo: String, season: Int, country: String, userId: String
    ) = viewModelScope.launch {
        addFavouriteLeagueResponse = FavouriteLeaguesResponse.Loading
        addFavouriteLeagueResponse = useCases.addFavouriteLeagues(id, name, logo, season, country, userId)
    }

    fun removeFavouriteLeague(id: Int, userId: String) = viewModelScope.launch {
        removeFavouriteLeagueResponse = FavouriteLeaguesResponse.Loading
        removeFavouriteLeagueResponse = useCases.removeFavouriteLeague(id, userId)
    }

    val networkStatus: StateFlow<NetworkStatus> = networkConnectivityService.networkStatus.stateIn(
        initialValue = NetworkStatus.Unknown,
        scope = viewModelScope,
        started = WhileSubscribed(5000)
    )
}