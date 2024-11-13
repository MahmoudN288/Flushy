package com.elTohamy.flushy.presentation.activities.players.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.elTohamy.flushy.data.repos.FlushyRepository
import com.elTohamy.flushy.data.state.PlayerByIdState
import com.elTohamy.flushy.data.state.PlayerSeasonsState
import com.elTohamy.flushy.data.state.PlayerSidelinedState
import com.elTohamy.flushy.data.state.PlayerSquadsState
import com.elTohamy.flushy.data.state.PlayerTransfersState
import com.elTohamy.flushy.data.state.PlayerTrophiesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val flushyRepository: FlushyRepository): ViewModel() {


    //Player Local
    private var _playerByIdState = MutableStateFlow<PlayerByIdState>(PlayerByIdState.Empty)
    val playerByIdState: StateFlow<PlayerByIdState> = _playerByIdState
    private var _isPlayerByIdInitialized = mutableStateOf(false)
    var isPlayerByIdInitialized: MutableState<Boolean> = _isPlayerByIdInitialized
    suspend fun getPlayerById(id: Int, season: Int) {
        _playerByIdState.value = PlayerByIdState.Loading

        try {
            val playerByIdResponse = flushyRepository.getPlayerById(id, season)
            _playerByIdState.value = PlayerByIdState.Success(data = playerByIdResponse)
            Log.d("Tag", "Success: $playerByIdResponse ")
        }
        catch (exception: HttpException) {
            _playerByIdState.value = PlayerByIdState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _playerByIdState.value = PlayerByIdState.Error("No internet connection")
        }
    }

    //Players Transfers
    private var _playerTransfersState = MutableStateFlow<PlayerTransfersState>(PlayerTransfersState.Empty)
    val playerTransfersState: StateFlow<PlayerTransfersState> = _playerTransfersState
    private var _isPlayerTransfersInitialized = mutableStateOf(false)
    var isPlayerTransfersInitialized: MutableState<Boolean> = _isPlayerTransfersInitialized
    suspend fun getPlayerTransfers(player: Int) {
        _playerTransfersState.value = PlayerTransfersState.Loading

        try {
            val playerTransfersResponse = flushyRepository.getPlayersTransfers(player)
            _playerTransfersState.value = PlayerTransfersState.Success(data = playerTransfersResponse)
            Log.d("Tag", "Success: $playerTransfersResponse ")
        }
        catch (exception: HttpException) {
            _playerTransfersState.value = PlayerTransfersState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _playerTransfersState.value = PlayerTransfersState.Error("No internet connection")
        }
    }


    //Players Sidelined
    private var _playerSidelinedState = MutableStateFlow<PlayerSidelinedState>(PlayerSidelinedState.Empty)
    val playerSidelinedState: StateFlow<PlayerSidelinedState> = _playerSidelinedState
    private var _isPlayerSidelinedInitialized = mutableStateOf(false)
    var isPlayerSidelinedInitialized: MutableState<Boolean> = _isPlayerSidelinedInitialized
    suspend fun getPlayerSidelined(player: Int) {
        _playerSidelinedState.value = PlayerSidelinedState.Loading

        try {
            val playerSidelinedResponse = flushyRepository.getPlayersSidelined(player)
            _playerSidelinedState.value = PlayerSidelinedState.Success(data = playerSidelinedResponse)
            Log.d("Tag", "Success: $playerSidelinedResponse ")
        }
        catch (exception: HttpException) {
            _playerSidelinedState.value = PlayerSidelinedState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _playerSidelinedState.value = PlayerSidelinedState.Error("No internet connection")
        }
    }


    //Players Trophies
    private var _playerTrophiesState = MutableStateFlow<PlayerTrophiesState>(PlayerTrophiesState.Empty)
    val playerTrophiesState: StateFlow<PlayerTrophiesState> = _playerTrophiesState
    private var _isPlayerTrophiesInitialized = mutableStateOf(false)
    var isPlayerTrophiesInitialized: MutableState<Boolean> = _isPlayerTrophiesInitialized
    suspend fun getPlayerTrophies(player: Int) {
        _playerTrophiesState.value = PlayerTrophiesState.Loading

        try {
            val playerTrophiesResponse = flushyRepository.getPlayersTrophies(player)
            _playerTrophiesState.value = PlayerTrophiesState.Success(data = playerTrophiesResponse)
            Log.d("Tag", "Success: $playerTrophiesResponse ")
        }
        catch (exception: HttpException) {
            _playerTrophiesState.value = PlayerTrophiesState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _playerTrophiesState.value = PlayerTrophiesState.Error("No internet connection")
        }
    }



    //Players Squads
    private var _playerSquadsState = MutableStateFlow<PlayerSquadsState>(PlayerSquadsState.Empty)
    val playerSquadsState: StateFlow<PlayerSquadsState> = _playerSquadsState
    private var _isPlayerSquadsInitialized = mutableStateOf(false)
    var isPlayerSquadsInitialized: MutableState<Boolean> = _isPlayerSquadsInitialized
    suspend fun getPlayerSquads(player: Int) {
        _playerSquadsState.value = PlayerSquadsState.Loading

        try {
            val playerSquadsResponse = flushyRepository.getPlayersSquads(player)
            _playerSquadsState.value = PlayerSquadsState.Success(data = playerSquadsResponse)
            Log.d("Tag", "Success: $playerSquadsResponse ")
        }
        catch (exception: HttpException) {
            _playerSquadsState.value = PlayerSquadsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _playerSquadsState.value = PlayerSquadsState.Error("No internet connection")
        }
    }



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