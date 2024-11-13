package com.elTohamy.flushy.presentation.activities.team.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elTohamy.flushy.data.remote.dto.fixtures.FixturesResponseItem
import com.elTohamy.flushy.data.repos.FlushyRepository
import com.elTohamy.flushy.data.repos.news.TeamNewsRepository
import com.elTohamy.flushy.data.state.PlayerSeasonsState
import com.elTohamy.flushy.data.state.TeamInfoState
import com.elTohamy.flushy.data.state.TeamMatchesState
import com.elTohamy.flushy.data.state.TeamNewsState
import com.elTohamy.flushy.data.state.TeamPlayersState
import com.elTohamy.flushy.data.state.TeamSeasonsState
import com.elTohamy.flushy.data.state.TeamTransfersState
import com.elTohamy.flushy.data.state.VenuesByIdState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val flushyRepository: FlushyRepository,
    private val teamNewsRepository: TeamNewsRepository
): ViewModel() {


    //Team Info
    private var _teamInfoByIdState= MutableStateFlow<TeamInfoState>(TeamInfoState.Empty)
    val teamInfoByIdState: StateFlow<TeamInfoState> = _teamInfoByIdState
    private var _isTeamInfoByIdInitialized = mutableStateOf(false)
    var isTeamInfoByIdInitialized: MutableState<Boolean> = _isTeamInfoByIdInitialized
    suspend fun getTeamInfoById(id: Int) {
        _teamInfoByIdState.value = TeamInfoState.Loading

        try {
            val teamInfoByIdResponse = flushyRepository.getTeamInfoById(id)
            _teamInfoByIdState.value = TeamInfoState.Success(data = teamInfoByIdResponse)
            Log.d("Tag", "Success: $teamInfoByIdResponse ")
        }
        catch (exception: HttpException) {
            _teamInfoByIdState.value = TeamInfoState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _teamInfoByIdState.value = TeamInfoState.Error("No internet connection")
        }
    }


    //Team Matches
    private var _teamMatchesState= MutableStateFlow<TeamMatchesState>(TeamMatchesState.Empty)
    val teamMatchesState: StateFlow<TeamMatchesState> = _teamMatchesState
    private var _isTeamMatchesInitialized = mutableStateOf(false)
    var isTeamMatchesInitialized: MutableState<Boolean> = _isTeamMatchesInitialized
    suspend fun getTeamMatches(team: Int, season: Int) {
        _teamMatchesState.value = TeamMatchesState.Loading

        try {
            val teamMatchesResponse = flushyRepository.getTeamMatches(team, season)
            _teamMatchesState.value = TeamMatchesState.Success(data = teamMatchesResponse)
            Log.d("Tag", "Success: $teamMatchesResponse ")
        }
        catch (exception: HttpException) {
            _teamMatchesState.value = TeamMatchesState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _teamMatchesState.value = TeamMatchesState.Error("No internet connection")
        }
    }


    //Team Seasons
    private var _teamSeasonsState = MutableStateFlow<TeamSeasonsState>(TeamSeasonsState.Empty)
    val teamSeasonsState: StateFlow<TeamSeasonsState> = _teamSeasonsState
    private var _isTeamSeasonsInitialized = mutableStateOf(false)
    var isTeamSeasonsInitialized: MutableState<Boolean> = _isTeamSeasonsInitialized
    suspend fun getTeamSeasons(team: Int) {
        _teamSeasonsState.value = TeamSeasonsState.Loading

        try {
            val teamSeasonsResponse = flushyRepository.getTeamSeasons(team)
            _teamSeasonsState.value = TeamSeasonsState.Success(data = teamSeasonsResponse)
            Log.d("Tag", "Success: $teamSeasonsResponse ")
        }
        catch (exception: HttpException) {
            _teamSeasonsState.value = TeamSeasonsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _teamSeasonsState.value = TeamSeasonsState.Error("No internet connection")
        }
    }


    //Team Players
    private var _teamPlayersState = MutableStateFlow<TeamPlayersState>(TeamPlayersState.Empty)
    val teamPlayersState: StateFlow<TeamPlayersState> = _teamPlayersState
    private var _isTeamPlayersInitialized = mutableStateOf(false)
    var isTeamPlayersInitialized: MutableState<Boolean> = _isTeamPlayersInitialized
    suspend fun getTeamPlayers(team: Int) {
        _teamPlayersState.value = TeamPlayersState.Loading

        try {
            val teamPlayersResponse = flushyRepository.getTeamPlayers(team)
            _teamPlayersState.value = TeamPlayersState.Success(data = teamPlayersResponse)
            Log.d("Tag", "Success: $teamPlayersResponse ")
        }
        catch (exception: HttpException) {
            _teamPlayersState.value = TeamPlayersState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _teamPlayersState.value = TeamPlayersState.Error("No internet connection")
        }
    }


    //Team Transfers
    private var _teamTransfersState = MutableStateFlow<TeamTransfersState>(TeamTransfersState.Empty)
    val teamTransfersState: StateFlow<TeamTransfersState> = _teamTransfersState
    private var _isTeamTransfersInitialized = mutableStateOf(false)
    var isTeamTransfersInitialized: MutableState<Boolean> = _isTeamTransfersInitialized
    suspend fun getTeamTransfers(team: Int) {
        _teamTransfersState.value = TeamTransfersState.Loading

        try {
            val teamTransfersResponse = flushyRepository.getTeamTransfers(team)
            _teamTransfersState.value = TeamTransfersState.Success(data = teamTransfersResponse)
            Log.d("Tag", "Success: $teamTransfersResponse ")
        }
        catch (exception: HttpException) {
            _teamTransfersState.value = TeamTransfersState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _teamTransfersState.value = TeamTransfersState.Error("No internet connection")
        }
    }



    //Venues
    private var _venuesByIdState = MutableStateFlow<VenuesByIdState>(VenuesByIdState.Empty)
    val venuesByIdState: StateFlow<VenuesByIdState> = _venuesByIdState
    private var _isVenuesByIdInitialized = mutableStateOf(false)
    var isVenuesByIdInitialized: MutableState<Boolean> = _isVenuesByIdInitialized
    //
    var isDialogShown by mutableStateOf(false)
        private set
    private var _venueId = mutableIntStateOf(0)
    val venueId: MutableState<Int> = _venueId
    fun onDialogOpened(){
        isDialogShown = true
    }
    fun onDismiss() {
        isDialogShown = false
    }
    fun setId(id: Int) {
        viewModelScope.launch {
            _venueId.intValue = id
        }
    }
    suspend fun getVenuesById(id: Int) {
        _venuesByIdState.value = VenuesByIdState.Loading

        try {
            val venueByIdResponse = flushyRepository.getVenuesById(id)
            _venuesByIdState.value = VenuesByIdState.Success(data = venueByIdResponse)
            Log.d("Tag", "Success: $venueByIdResponse ")
        }
        catch (exception: HttpException) {
            _venuesByIdState.value = VenuesByIdState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _venuesByIdState.value = VenuesByIdState.Error("No internet connection")
        }
    }

    //News
    private var _teamNewsState = MutableStateFlow<TeamNewsState>(TeamNewsState.Empty)
    val teamNewsState: StateFlow<TeamNewsState> = _teamNewsState
    private var _isTeamNewsStateInitialized = mutableStateOf(false)
    var isTeamNewsStateInitialized: MutableState<Boolean> = _isTeamNewsStateInitialized
    suspend fun getTeamNews(language: String, country: String, q1: String, q2: String) {
        _teamNewsState.value = TeamNewsState.Loading

        try {
            val teamNewsResponse = teamNewsRepository.getTeamNews(language, country, q1, q2)
            _teamNewsState.value = TeamNewsState.Success(data = teamNewsResponse)
            Log.d("Tag", "Success: $teamNewsResponse ")
        }
        catch (exception: HttpException) {
            _teamNewsState.value = TeamNewsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _teamNewsState.value = TeamNewsState.Error("No internet connection")
        }
    }


    //Save Fetched Lists
    //Matches List By Date
    private var _mainList = mutableStateOf<List<FixturesResponseItem?>>(listOf())
    var mainList: MutableState<List<FixturesResponseItem?>> = _mainList



    //Players Seasons
    private var _playerSeasonsState = MutableStateFlow<PlayerSeasonsState>(PlayerSeasonsState.Empty)
    val playerSeasonsState: StateFlow<PlayerSeasonsState> = _playerSeasonsState
    private var _isPlayerSeasonsInitialized = mutableStateOf(false)
    var isPlayerSeasonsInitialized: MutableState<Boolean> = _isPlayerSeasonsInitialized
    suspend fun getPlayerSeasons(player: Int) {
        _playerSeasonsState.value = PlayerSeasonsState.Loading

        try {
            val playerSeasonsResponse = flushyRepository.getPlayersSeasons(player)
            _playerSeasonsState.value = PlayerSeasonsState.Success(data = playerSeasonsResponse)
            Log.d("Tag", "Success: $playerSeasonsResponse ")
        }
        catch (exception: HttpException) {
            _playerSeasonsState.value = PlayerSeasonsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _playerSeasonsState.value = PlayerSeasonsState.Error("No internet connection")
        }
    }
}