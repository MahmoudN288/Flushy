package com.elTohamy.flushy.data.repos

import com.elTohamy.flushy.data.remote.api.FlushyApi
import com.elTohamy.flushy.data.remote.dto.fixtures.Fixtures
import com.elTohamy.flushy.data.remote.dto.fixtures.byId.MatchByIdResponse
import com.elTohamy.flushy.data.remote.dto.fixtures.headToHead.Head2Head
import com.elTohamy.flushy.data.remote.dto.fixtures.lineups.Lineups
import com.elTohamy.flushy.data.remote.dto.fixtures.players.PlayersStatistics
import com.elTohamy.flushy.data.remote.dto.fixtures.today.TodayFixtures
import com.elTohamy.flushy.data.remote.dto.leagues.Leagues
import com.elTohamy.flushy.data.remote.dto.leagues.leagueById.LeaguesById
import com.elTohamy.flushy.data.remote.dto.players.Players
import com.elTohamy.flushy.data.remote.dto.players.seasons.PlayerSeasons
import com.elTohamy.flushy.data.remote.dto.players.squads.Squads
import com.elTohamy.flushy.data.remote.dto.players.topAssists.TopAssists
import com.elTohamy.flushy.data.remote.dto.players.topRedCards.TopRedCards
import com.elTohamy.flushy.data.remote.dto.players.topScorers.TopScorers
import com.elTohamy.flushy.data.remote.dto.players.topYellowCards.TopYellowCards
import com.elTohamy.flushy.data.remote.dto.sidelined.Sidelined
import com.elTohamy.flushy.data.remote.dto.standings.Standings
import com.elTohamy.flushy.data.remote.dto.teams.TeamsInfo
import com.elTohamy.flushy.data.remote.dto.teams.seasons.TeamSeasons
import com.elTohamy.flushy.data.remote.dto.teams.squads.TeamSquadResponse
import com.elTohamy.flushy.data.remote.dto.teams.stats.TeamStats
import com.elTohamy.flushy.data.remote.dto.timezone.Timezone
import com.elTohamy.flushy.data.remote.dto.transfers.Transfers
import com.elTohamy.flushy.data.remote.dto.trophies.Trophies
import com.elTohamy.flushy.data.remote.dto.venues.Venues
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class FlushyRepository @Inject constructor(
    private val flushyApiService: FlushyApi
) {
    suspend fun getLiveMatches(): Response<Fixtures> = flushyApiService.getLiveMatches()
    fun getTimezone(): Call<Timezone> = flushyApiService.getTimezone()
    suspend fun getTodayMatches(date: String, timezone: String): Response<TodayFixtures> =
        flushyApiService.getTodayMatches(date, timezone)
    suspend fun getPLStandings(league: Int, season: Int): Response<Standings> =
        flushyApiService.getLeagueStandings(league, season)
    suspend fun getPLLeagueInfo(id: Int): Response<LeaguesById> = flushyApiService.getLeagueInfo(id)
    suspend fun getLeagueTeamStats(league: Int, season: Int, team: Int): Response<TeamStats> =
        flushyApiService.getLeagueTeamStats(league, season, team)
    suspend fun getMatchInfoById(id: Int, timezone: String):
            Response<MatchByIdResponse> = flushyApiService.getMatchInfoById(id, timezone)
    suspend fun getHeadToHead(ids: String): Response<Head2Head> = flushyApiService.getHeadToHead(ids)
    suspend fun getMatchesByLeague(league: Int, season: Int, timezone: String): Response<Fixtures> =
        flushyApiService.getMatchesByLeague(league, season, timezone)
    //Venues
    suspend fun getVenuesById(id: Int): Response<Venues> = flushyApiService.getVenuesById(id)
    //Lineups By Fixture Id
    suspend fun getLineupsByFixtureId(id: Int): Response<Lineups> = flushyApiService.getLineups(id)
    suspend fun getPlayersStatsByFixture(id: Int): Response<PlayersStatistics> =
        flushyApiService.getPlayersStatsByFixture(id)
    //Player Activity
    suspend fun getPlayerById(id: Int, season: Int): Response<Players> = flushyApiService.getPlayersById(id, season)
    suspend fun getPlayersTransfers(player: Int): Response<Transfers> = flushyApiService.getPlayersTransfers(player)
    suspend fun getPlayersSidelined(player: Int): Response<Sidelined> = flushyApiService.getPlayersSidelined(player)
    suspend fun getPlayersTrophies(player: Int): Response<Trophies> = flushyApiService.getPlayersTrophies(player)
    suspend fun getPlayersSquads(player: Int): Response<Squads> = flushyApiService.getPlayersSquads(player)
    suspend fun getPlayersSeasons(player: Int): Response<PlayerSeasons> = flushyApiService.getPlayersSeasons(player)
    //Team Activity
    suspend fun getTeamInfoById(id: Int): Response<TeamsInfo> = flushyApiService.getTeamInfoById(id)
    suspend fun getTeamMatches(team: Int, season: Int): Response<Fixtures> = flushyApiService.getTeamMatches(team, season)
    suspend fun getTeamSeasons(team: Int): Response<TeamSeasons> = flushyApiService.getTeamSeasons(team)
    suspend fun getTeamPlayers(team: Int): Response<TeamSquadResponse> = flushyApiService.getTeamPlayers(team)
    suspend fun getTeamTransfers(team: Int): Response<Transfers> = flushyApiService.getTeamTransfers(team)
    //
    suspend fun getTeamsById(id: Int): Response<TeamsInfo> = flushyApiService.getTeamsById(id)
    suspend fun getLeagues(): Response<Leagues> = flushyApiService.getLeagues()

    //Tournament Activity
    suspend fun getTopScorersByLeague(league: Int, season: Int): Response<TopScorers> =
        flushyApiService.getTopScorersByLeague(league, season)
    suspend fun getTopAssistsByLeague(league: Int, season: Int): Response<TopAssists> =
        flushyApiService.getTopAssistsByLeague(league, season)
    suspend fun getTopYellowCardsByLeague(league: Int, season: Int): Response<TopYellowCards> =
        flushyApiService.getTopYellowCardsByLeague(league, season)
    suspend fun getTopRedCardsByLeague(league: Int, season: Int): Response<TopRedCards> =
        flushyApiService.getTopRedCardsByLeague(league, season)

    //Player In Local
    /*fun loadItems(id: Int): LiveData<List<PlayerInfoResponseItemEntity>> {
        return liveData {

            val getPlayerFromNetwork = flushyApiService.getPlayersById(id)
            playerDao.insertPlayer(getPlayerFromNetwork)

            //load from local
            val loadPlayerFromLocal = playerDao.getPlayerItems()

            emitSource(loadPlayerFromLocal)
        }
    }*/
}