package com.elTohamy.flushy.presentation.activities.match.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elTohamy.flushy.data.state.LineupsState
import com.elTohamy.flushy.data.state.MatchInfoState
import com.elTohamy.flushy.data.state.MatchLeagueInfoState
import com.elTohamy.flushy.data.state.PlayersStatsByFixState
import com.elTohamy.flushy.data.state.VenuesByIdState
import com.elTohamy.flushy.data.repos.FlushyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val mainRepository: FlushyRepository
): ViewModel() {
    //League Info
    //League Info
    private var _leagueInfoState = MutableStateFlow<MatchLeagueInfoState>(MatchLeagueInfoState.Empty)
    val leagueInfoState: StateFlow<MatchLeagueInfoState> = _leagueInfoState
    private var _isInitializedLeagueInfo = mutableStateOf(false)
    var isInitializedLeagueInfo: MutableState<Boolean> = _isInitializedLeagueInfo
    suspend fun getLeagueInfo(id: Int) {
        _leagueInfoState.value = MatchLeagueInfoState.Loading

        try {
            val pLLeagueInfoResponse = mainRepository.getPLLeagueInfo(id)
            _leagueInfoState.value = MatchLeagueInfoState.Success(data = pLLeagueInfoResponse)
        }
        catch (exception: HttpException) {
            _leagueInfoState.value = MatchLeagueInfoState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _leagueInfoState.value = MatchLeagueInfoState.Error("No internet connection")
        }
    }

    //Match By Id
    //Events
    private var _isInitializedMatchInfo = mutableStateOf(false)
    var isInitializedMatchInfo: MutableState<Boolean> = _isInitializedMatchInfo
    //MatchState
    private var _matchInfoState = MutableStateFlow<MatchInfoState>(MatchInfoState.Empty)
    val matchInfoState: StateFlow<MatchInfoState> = _matchInfoState
    suspend fun getMatchInfo(id: Int, timezone: String) {
        _matchInfoState.value = MatchInfoState.Loading

        try {
            val matchData = mainRepository.getMatchInfoById(id, timezone)
            _matchInfoState.value = MatchInfoState.Success(data = matchData)
        }
        catch (exception: HttpException) {
            _matchInfoState.value = MatchInfoState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _matchInfoState.value = MatchInfoState.Error("No internet connection")
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
            val venueByIdResponse = mainRepository.getVenuesById(id)
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


    //Lineups
    private var _isInitializedLineups = mutableStateOf(false)
    var isInitializedLineups: MutableState<Boolean> = _isInitializedLineups
    //State
    private var _lineupsState = MutableStateFlow<LineupsState>(LineupsState.Empty)
    val lineupsState: StateFlow<LineupsState> = _lineupsState
    suspend fun getLineupsByFixtureId(id: Int) {
        _lineupsState.value = LineupsState.Loading

        try {
            val lineupsData = mainRepository.getLineupsByFixtureId(id)
            _lineupsState.value = LineupsState.Success(data = lineupsData)
        }
        catch (exception: HttpException) {
            _lineupsState.value = LineupsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _lineupsState.value = LineupsState.Error("No internet connection")
        }
    }

    //Lineups
    private var _isInitializedPlayersStatsByFix = mutableStateOf(false)
    var isInitializedPlayersStatsByFix: MutableState<Boolean> = _isInitializedPlayersStatsByFix
    //State
    private var _playersStatsByFixState = MutableStateFlow<PlayersStatsByFixState>(
        PlayersStatsByFixState.Empty)
    val playersStatsByFixState: StateFlow<PlayersStatsByFixState> = _playersStatsByFixState
    suspend fun getPlayersStatsByFixtureId(id: Int) {
        _playersStatsByFixState.value = PlayersStatsByFixState.Loading

        try {
            val playersStatsData = mainRepository.getPlayersStatsByFixture(id)
            _playersStatsByFixState.value = PlayersStatsByFixState.Success(data = playersStatsData)
        }
        catch (exception: HttpException) {
            _playersStatsByFixState.value = PlayersStatsByFixState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _playersStatsByFixState.value = PlayersStatsByFixState.Error("No internet connection")
        }
    }
}