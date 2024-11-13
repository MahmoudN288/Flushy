package com.elTohamy.flushy.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elTohamy.flushy.data.local.leagues.FavouriteLeagues
import com.elTohamy.flushy.data.local.leagues.repository.FavouriteLeaguesRepositoryImplementation
import com.elTohamy.flushy.data.local.news.SavedNews
import com.elTohamy.flushy.data.local.news.repository.SavedNewsRepositoryImplementation
import com.elTohamy.flushy.data.remote.dto.fixtures.today.TodayGamesResponseItem
import com.elTohamy.flushy.data.repos.FlushyRepository
import com.elTohamy.flushy.data.repos.news.MainNewsRepository
import com.elTohamy.flushy.data.state.LeagueStatsState
import com.elTohamy.flushy.data.state.LeaguesState
import com.elTohamy.flushy.data.state.NewsState
import com.elTohamy.flushy.data.state.SavedNewsState
import com.elTohamy.flushy.data.state.TeamByIdState
import com.elTohamy.flushy.data.state.TodayMatchesState
import com.elTohamy.flushy.presentation.model.LeagueWithMatches
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: FlushyRepository,
    private val mainNewsRepository: MainNewsRepository,
    private val favouriteLeaguesRepositoryImplementation: FavouriteLeaguesRepositoryImplementation,
    private val savedNewsRepositoryImplementation: SavedNewsRepositoryImplementation
): ViewModel() {

    var isDialogLanguageShown by mutableStateOf(false)
    fun onDialogLanguageOpened(){
        isDialogLanguageShown = true
    }
    fun onDismissLanguage() {
        isDialogLanguageShown = false
    }

    //Teams Stats
    private var _leagueStatsState = MutableStateFlow<LeagueStatsState>(LeagueStatsState.Empty)
    val leagueStatsState: StateFlow<LeagueStatsState> = _leagueStatsState

    //Save Fetched Lists
    //Matches List By Date
    private var _mainList = mutableStateOf<List<LeagueWithMatches>>(emptyList())
    var mainList: MutableState<List<LeagueWithMatches>> = _mainList

    //private var refreshingJob: Job? = null
    /*if (refreshingJob != null) return
        refreshingJob = viewModelScope.launch(Dispatchers.IO) {

            while(true) {

                _liveMatchesState.value = MatchState.Loading
                try {
                    val liveMatchesResponse = liveMatchesRepository.getLiveMatches()
                    _liveMatchesState.value = MatchState.Success(liveMatchesResponse)
                } catch (exception: HttpException) {
                    _liveMatchesState.value = MatchState.Error("Something went wrong")
                } catch (exception: IOException) {
                    _liveMatchesState.value = MatchState.Error("No internet connection")
                }

                delay(20000)  // wait for 20 seconds
            }
        }
    }

    // This will be called when the ViewModel is going to be destroyed
    override fun onCleared() {
        super.onCleared()
        refreshingJob?.cancel()
    }*/

    //Today Matches
    private var _todayMatchesState = MutableStateFlow<TodayMatchesState>(TodayMatchesState.Empty)
    val todayMatchesState: StateFlow<TodayMatchesState> = _todayMatchesState
    private var _isInitializedMain = mutableStateOf(false)
    var isInitializedMain: MutableState<Boolean> = _isInitializedMain
    suspend fun getTodayMatches(date: String, timezone: String) {
        _todayMatchesState.value = TodayMatchesState.Loading

        try {
            val todayMatchesResponse = mainRepository.getTodayMatches(date, timezone)
            _todayMatchesState.value = TodayMatchesState.Success(todayMatchesResponse)
        }
        catch (exception: HttpException) {
            _todayMatchesState.value = TodayMatchesState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _todayMatchesState.value = TodayMatchesState.Error("No internet connection")
        }
    }

    //League Stats
    suspend fun getLeagueStats(league: Int, season: Int, team: Int) {
        _leagueStatsState.value = LeagueStatsState.Loading

        try {
            val leagueStatsResponse = mainRepository.getLeagueTeamStats(league, season, team)
            _leagueStatsState.value = LeagueStatsState.Success(
                data = leagueStatsResponse,
                teamData = leagueStatsResponse.body()!!.response!!
            )
        }
        catch (exception: HttpException) {
            _leagueStatsState.value = LeagueStatsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _leagueStatsState.value = LeagueStatsState.Error("No internet connection")
        }
    }



    //Teams Local
    private var _teamsByIdState = MutableStateFlow<TeamByIdState>(TeamByIdState.Empty)
    val teamsByIdState: StateFlow<TeamByIdState> = _teamsByIdState
    private var _isTeamByIdInitialized = mutableStateOf(false)
    var isTeamByIdInitialized: MutableState<Boolean> = _isTeamByIdInitialized
    suspend fun getTeamById(id: Int) {
        _teamsByIdState.value = TeamByIdState.Loading

        try {
            val teamByIdResponse = mainRepository.getTeamsById(id)
            _teamsByIdState.value = TeamByIdState.Success(data = teamByIdResponse)
            Log.d("Tag", "Success: $teamByIdResponse ")
        }
        catch (exception: HttpException) {
            _teamsByIdState.value = TeamByIdState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _teamsByIdState.value = TeamByIdState.Error("No internet connection")
        }
    }

    //All Leagues
    private var _leaguesState = MutableStateFlow<LeaguesState>(LeaguesState.Empty)
    val leaguesState: StateFlow<LeaguesState> = _leaguesState
    private var _isLeaguesInitialized = mutableStateOf(false)
    var isLeaguesInitialized: MutableState<Boolean> = _isLeaguesInitialized
    suspend fun getLeagues() {
        _leaguesState.value = LeaguesState.Loading

        try {
            val teamByIdResponse = mainRepository.getLeagues()
            _leaguesState.value = LeaguesState.Success(data = teamByIdResponse)
            Log.d("Tag", "Success: $teamByIdResponse ")
        }
        catch (exception: HttpException) {
            _leaguesState.value = LeaguesState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _leaguesState.value = LeaguesState.Error("No internet connection")
        }
    }



    //All News
    private var _newsState = MutableStateFlow<NewsState>(NewsState.Empty)
    val newsState: StateFlow<NewsState> = _newsState
    private var _isNewsInitialized = mutableStateOf(false)
    var isNewsInitialized: MutableState<Boolean> = _isNewsInitialized
    suspend fun getAllNews(language: String, country: String, q: String) {
        _newsState.value = NewsState.Loading

        try {
            val allNewsResponse = mainNewsRepository.getNews(language, country, q)
            _newsState.value = NewsState.Success(data = allNewsResponse)
            Log.d("Tag", "Success: $allNewsResponse ")
        }
        catch (exception: HttpException) {
            _newsState.value = NewsState.Error("Something went wrong")
        }
        catch (exception: IOException) {
            _newsState.value = NewsState.Error("No internet connection")
        }
    }


    //Local
    //All Favourite League
    fun addLeague(favouriteLeagues: FavouriteLeagues) = viewModelScope.launch(Dispatchers.IO) {
        favouriteLeaguesRepositoryImplementation.insertLeague(favouriteLeagues)
    }
    fun deleteLeague(favouriteLeagues: FavouriteLeagues) = viewModelScope.launch(Dispatchers.IO) {
        favouriteLeaguesRepositoryImplementation.deleteLeague(favouriteLeagues)
    }
    fun isRowExists(id: Int) = favouriteLeaguesRepositoryImplementation.isRowExists(id)
    suspend fun getAllFavouriteLeagues(): Flow<List<FavouriteLeagues>> {
        return withContext(Dispatchers.IO) {
            favouriteLeaguesRepositoryImplementation.getAllLeagues()
        }
    }


    //Saved News In Room
    private val _savedNewsState = MutableStateFlow<SavedNewsState>(SavedNewsState.Removed)
    val savedNewsState: StateFlow<SavedNewsState> = _savedNewsState
    fun addOnSavedNews(savedNews: SavedNews) = viewModelScope.launch(Dispatchers.IO) {
        savedNewsRepositoryImplementation.addOneSavedNews(savedNews)
        _savedNewsState.value = SavedNewsState.Added
    }
    fun deleteOneSavedNews(savedNews: SavedNews) = viewModelScope.launch(Dispatchers.IO) {
        savedNewsRepositoryImplementation.deleteOneSavedNews(savedNews)
        _savedNewsState.value = SavedNewsState.Removed
    }
    fun isNewsSaved(title: String) = savedNewsRepositoryImplementation.isNewsSaved(title)

    private val _savedNews = MutableStateFlow<List<SavedNews>>(emptyList())
    val savedNews: StateFlow<List<SavedNews>> = _savedNews
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _savedNews.value = savedNewsRepositoryImplementation.getAllSavedNews()
        }
    }

    //Save LazyColumn State
    private val _lazyListStateAll = mutableIntStateOf(0)
    val lazyListStateAll: MutableState<Int> = _lazyListStateAll

    fun updateLazyListStateAll(state: Int) {
        _lazyListStateAll.intValue = state
    }

    private val _lazyListStateFavourite = mutableIntStateOf(0)
    val lazyListStateFavourite: MutableState<Int> = _lazyListStateFavourite

    fun updateLazyListStateFavourite(state: Int) {
        _lazyListStateFavourite.intValue = state
    }
}