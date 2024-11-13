package com.elTohamy.flushy.presentation.activities.tournament.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.elTohamy.flushy.data.state.LeagueStandingsState
import com.elTohamy.flushy.data.state.MatchesByLeagueState
import com.elTohamy.flushy.data.state.TopAssistsState
import com.elTohamy.flushy.data.state.TopRedCardsState
import com.elTohamy.flushy.data.state.TopScorersState
import com.elTohamy.flushy.data.state.TopYellowCardsState
import com.elTohamy.flushy.data.state.TournamentLeagueInfoState
import com.elTohamy.flushy.data.state.TournamentNewsState
import com.elTohamy.flushy.data.remote.dto.standings.StandingsItemItem
import com.elTohamy.flushy.data.repos.FlushyRepository
import com.elTohamy.flushy.data.repos.news.TournamentNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

fun getWrongAnswers(N: Int, C: String): String {
    val answerA = 'A'
    val answerB = 'B'

    val result = StringBuilder(C)

    for(i in C.indices) {
        when(C[i]) {
            answerA -> result.setCharAt(i, answerB)
            answerB -> result.setCharAt(i, answerA)
        }
    }
    return result.toString()
}

fun getHitProbability(R: Int, C: Int, G: Array<Array<Int>>): Double {
    val n = R * C
    var battleships = 0
    for (i in 0 until R) {
        for (j in 0 until C) {
            if (G[i][j] == 1) {
                battleships++
            }
        }
    }
    return if (n > 0) battleships.toDouble() / n else 0.0
}

fun getMaxAdditionalDinersCount(N: Long, K: Long, M: Int, S: Array<Long>): Long {
    val seats = BooleanArray(N.toInt()) { index ->
        // Initialize with true if index + 1 is not in S, otherwise false
        !(S.contains(index.toLong() + 1))
    }

    // Mark occupied seats and surrounding K seats
    for (seat in S) {
        seats[(seat - 1).toInt()] = false // Mark occupied seat
        for (i in maxOf(0L, seat - K - 1) until minOf(N, seat + K)) {
            seats[i.toInt()] = false // Mark surrounding K seats
        }
    }

    var additionalDiners: Long = 0
    var lastOccupied: Long = -K - 1 // Initialize last occupied seat

    // Count available seats with K gap
    for (i in 0L until N) {
        if (seats[i.toInt()] && i - lastOccupied > K) {
            additionalDiners++
            lastOccupied = i
        }
    }

    return additionalDiners
}

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val mainRepository: FlushyRepository,
    private val tournamentNewsRepository: TournamentNewsRepository
): ViewModel() {
    //League Info
    //League Info
    private var _leagueInfoState = MutableStateFlow<TournamentLeagueInfoState>(
        TournamentLeagueInfoState.Empty)
    val leagueInfoState: StateFlow<TournamentLeagueInfoState> = _leagueInfoState
    private var _isInitializedLeagueInfo = mutableStateOf(false)
    var isInitializedLeagueInfo: MutableState<Boolean> = _isInitializedLeagueInfo
    suspend fun getLeagueInfo(id: Int) {
        _leagueInfoState.value = TournamentLeagueInfoState.Loading

        try {
            val pLLeagueInfoResponse = mainRepository.getPLLeagueInfo(id)
            _leagueInfoState.value = TournamentLeagueInfoState.Success(data = pLLeagueInfoResponse)
        }
        catch (exception: HttpException) {
            _leagueInfoState.value = TournamentLeagueInfoState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _leagueInfoState.value = TournamentLeagueInfoState.Error("No internet connection")
        }
    }

    //Tournament Activity
    //Tab Screen
    //Call Limit
    private var _isInitializedStandings = mutableStateOf(false)
    var isInitializedStandings: MutableState<Boolean> = _isInitializedStandings
    private var _isInitializedStandingsTournament = mutableStateOf(false)
    var isInitializedStandingsTournament: MutableState<Boolean> = _isInitializedStandingsTournament

    //Standings
    private var _leagueStandingsState = MutableStateFlow<LeagueStandingsState>(LeagueStandingsState.Empty)
    val leagueStandingsState: StateFlow<LeagueStandingsState> = _leagueStandingsState
    suspend fun getLeagueStandings(league: Int, season: Int) {
        _leagueStandingsState.value = LeagueStandingsState.Loading

        try {
            val pLStandingsResponse = mainRepository.getPLStandings(league, season)
                .body()!!.response?.get(0)!!.league!!.standings
            _leagueStandingsState.value = LeagueStandingsState.Success(list = pLStandingsResponse!![0]!!, listOfList = pLStandingsResponse)
        }
        catch (exception: HttpException) {
            _leagueStandingsState.value = LeagueStandingsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _leagueStandingsState.value = LeagueStandingsState.Error("No internet connection")
        }
    }
    //Standings List
    private var _standingsListTournament = mutableStateOf<List<List<StandingsItemItem?>?>?>(listOf())
    var standingsListTournament: MutableState<List<List<StandingsItemItem?>?>?> = _standingsListTournament



    //Matches By League
    private var _matchesByLeagueState = MutableStateFlow<MatchesByLeagueState>(MatchesByLeagueState.Empty)
    val matchesByLeagueState: StateFlow<MatchesByLeagueState> = _matchesByLeagueState
    private var _isTournamentMatchesInitialized = mutableStateOf(false)
    var isTournamentMatchesInitialized: MutableState<Boolean> = _isTournamentMatchesInitialized
    suspend fun getMatchesByLeague(id: Int, season: Int, timezone: String) {
        _matchesByLeagueState.value = MatchesByLeagueState.Loading

        try {
            val date = mainRepository.getMatchesByLeague(league = id, season = season, timezone = timezone)
            _matchesByLeagueState.value = MatchesByLeagueState.Success(data = date)
        }
        catch (exception: HttpException) {
            _matchesByLeagueState.value = MatchesByLeagueState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _matchesByLeagueState.value = MatchesByLeagueState.Error("No internet connection")
        }
    }


    //Top Scorers
    private var _topScorersState = MutableStateFlow<TopScorersState>(TopScorersState.Empty)
    val topScorersState: StateFlow<TopScorersState> = _topScorersState
    private var _isTopScorersInitialized = mutableStateOf(false)
    var isTopScorersInitialized: MutableState<Boolean> = _isTopScorersInitialized
    suspend fun getTopScorersByLeague(league: Int, season: Int) {
        _topScorersState.value = TopScorersState.Loading

        try {
            val topScorersByLeagueResponse = mainRepository.getTopScorersByLeague(league, season)
            _topScorersState.value = TopScorersState.Success(data = topScorersByLeagueResponse)
            Log.d("Tag", "Success: $topScorersByLeagueResponse ")
        }
        catch (exception: HttpException) {
            _topScorersState.value = TopScorersState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _topScorersState.value = TopScorersState.Error("No internet connection")
        }
    }

    //Top Assists
    private var _topAssistsState = MutableStateFlow<TopAssistsState>(TopAssistsState.Empty)
    val topAssistsState: StateFlow<TopAssistsState> = _topAssistsState
    private var _isTopAssistsInitialized = mutableStateOf(false)
    var isTopAssistsInitialized: MutableState<Boolean> = _isTopAssistsInitialized
    suspend fun getTopAssistsByLeague(league: Int, season: Int) {
        _topAssistsState.value = TopAssistsState.Loading

        try {
            val topAssistsByLeagueResponse = mainRepository.getTopAssistsByLeague(league, season)
            _topAssistsState.value = TopAssistsState.Success(data = topAssistsByLeagueResponse)
            Log.d("Tag", "Success: $topAssistsByLeagueResponse ")
        }
        catch (exception: HttpException) {
            _topAssistsState.value = TopAssistsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _topAssistsState.value = TopAssistsState.Error("No internet connection")
        }
    }

    //Top Yellow
    private var _topYellowCardsState = MutableStateFlow<TopYellowCardsState>(TopYellowCardsState.Empty)
    val topYellowCardsState: StateFlow<TopYellowCardsState> = _topYellowCardsState
    private var _isTopYellowCardsInitialized = mutableStateOf(false)
    var isTopYellowCardsInitialized: MutableState<Boolean> = _isTopYellowCardsInitialized
    suspend fun getTopYellowCardsByLeague(league: Int, season: Int) {
        _topYellowCardsState.value = TopYellowCardsState.Loading

        try {
            val topYellowCardsByLeagueResponse = mainRepository.getTopYellowCardsByLeague(league, season)
            _topYellowCardsState.value = TopYellowCardsState.Success(data = topYellowCardsByLeagueResponse)
            Log.d("Tag", "Success: $topYellowCardsByLeagueResponse ")
        }
        catch (exception: HttpException) {
            _topYellowCardsState.value = TopYellowCardsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _topYellowCardsState.value = TopYellowCardsState.Error("No internet connection")
        }
    }

    //Top Red
    private var _topRedCardsState = MutableStateFlow<TopRedCardsState>(TopRedCardsState.Empty)
    val topRedCardsState: StateFlow<TopRedCardsState> = _topRedCardsState
    private var _isTopRedCardsInitialized = mutableStateOf(false)
    var isTopRedCardsInitialized: MutableState<Boolean> = _isTopRedCardsInitialized
    suspend fun getTopRedCardsByLeague(league: Int, season: Int) {
        _topRedCardsState.value = TopRedCardsState.Loading

        try {
            val topRedCardsByLeagueResponse = mainRepository.getTopRedCardsByLeague(league, season)
            _topRedCardsState.value = TopRedCardsState.Success(data = topRedCardsByLeagueResponse)
            Log.d("Tag", "Success: $topRedCardsByLeagueResponse ")
        }
        catch (exception: HttpException) {
            _topRedCardsState.value = TopRedCardsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _topRedCardsState.value = TopRedCardsState.Error("No internet connection")
        }
    }


    //News
    private var _tournamentNewsState = MutableStateFlow<TournamentNewsState>(TournamentNewsState.Empty)
    val tournamentNewsState: StateFlow<TournamentNewsState> = _tournamentNewsState
    private var _isTournamentNewsStateInitialized = mutableStateOf(false)
    var isTournamentNewsStateInitialized: MutableState<Boolean> = _isTournamentNewsStateInitialized
    suspend fun getTournamentNews(language: String, country: String, q: String) {
        _tournamentNewsState.value = TournamentNewsState.Loading

        try {
            val tournamentNewsResponse = tournamentNewsRepository.getTournamentNews(language, country, q)
            _tournamentNewsState.value = TournamentNewsState.Success(data = tournamentNewsResponse)
            Log.d("Tag", "Success: $tournamentNewsResponse ")
        }
        catch (exception: HttpException) {
            _tournamentNewsState.value = TournamentNewsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _tournamentNewsState.value = TournamentNewsState.Error("No internet connection")
        }
    }
}