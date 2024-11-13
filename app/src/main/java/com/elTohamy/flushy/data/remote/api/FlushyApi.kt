package com.elTohamy.flushy.data.remote.api

import com.elTohamy.flushy.data.local.database.PlayerInfoResponseItemEntity
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
import com.elTohamy.flushy.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlushyApi {
    //Timezone
    @GET(Constants.GET_TIMEZONE)
    fun getTimezone(): Call<Timezone>

    @GET(Constants.GET_FIXTURES)
    suspend fun getLiveMatches(
        @Query(Constants.FIXTURES_BY_LIVE_PARAM) live: String = "all",
        @Query(Constants.FIXTURES_BY_TIMEZONE_PARAM) timezone: String = "Africa/Cairo",
        @Query(Constants.FIXTURES_BY_SEASON_PARAM) season: Int = 2023
    ): Response<Fixtures>

    @GET(Constants.GET_FIXTURES)
    suspend fun getTodayMatches(
        @Query(Constants.FIXTURES_BY_DATE_PARAM) date: String,
        @Query(Constants.FIXTURES_BY_TIMEZONE_PARAM) timezone: String
    ): Response<TodayFixtures>

    @GET(Constants.GET_STANDINGS)
    suspend fun getLeagueStandings(
        @Query(Constants.STANDINGS_BY_LEAGUE_ID_PARAM) league: Int,
        @Query(Constants.STANDINGS_BY_SEASON_PARAM) season: Int
    ): Response<Standings>

    @GET(Constants.GET_LEAGUES)
    suspend fun getLeagueInfo(
        @Query(Constants.LEAGUES_ID_PARAM) id: Int
    ): Response<LeaguesById>

    @GET(Constants.GET_TEAMS_STATS)
    suspend fun getLeagueTeamStats(
        @Query(Constants.TEAMS_STATS_BY_LEAGUE_ID_PARAM) league: Int,
        @Query(Constants.TEAMS_STATS_BY_SEASON_PARAM) season: Int,
        @Query(Constants.TEAMS_STATS_BY_TEAM_ID_PARAM) team: Int
    ): Response<TeamStats>

    @GET(Constants.GET_FIXTURES)
    suspend fun getMatchInfoById(
        @Query(Constants.FIXTURES_BY_ID_PARAM) id: Int,
        @Query(Constants.FIXTURES_BY_TIMEZONE_PARAM) timezone: String
    ): Response<MatchByIdResponse>

    @GET(Constants.GET_FIXTURES_HEADTOHEAD)
    suspend fun getHeadToHead(
        @Query(Constants.FIXTURES_HEAD_TO_HEAD_BY_IDS_PARAM) ids: String
    ): Response<Head2Head>

    @GET(Constants.GET_FIXTURES)
    suspend fun getMatchesByLeague(
        @Query(Constants.FIXTURES_BY_LEAGUE_ID_PARAM) league: Int,
        @Query(Constants.FIXTURES_BY_SEASON_PARAM) season: Int,
        @Query(Constants.FIXTURES_BY_TIMEZONE_PARAM) timezone: String
    ): Response<Fixtures>

    //Venues
    @GET(Constants.GET_VENUES)
    suspend fun getVenuesById(
        @Query(Constants.VENUE_BY_ID_PARAM) id: Int
    ): Response<Venues>

    //Lineups
    @GET(Constants.GET_FIXTURES_LINEUPS)
    suspend fun getLineups(
        @Query(Constants.FIXTURES_LINEUPS_BY_FIXTURE_ID_PARAM) fixture: Int
    ): Response<Lineups>

    //Match Player's Stats
    @GET(Constants.GET_FIXTURES_PLAYERS_STATS)
    suspend fun getPlayersStatsByFixture(
        @Query(Constants.FIXTURES_PLAYERS_STATS_BY_FIXTURE_ID_PARAM) fixture: Int
    ): Response<PlayersStatistics>

    //Player
    @GET(Constants.GET_PLAYERS)
    suspend fun getPlayersByTeam(
        @Query(Constants.PLAYERS_BY_TEAM_ID_PARAM) team: Int,
        @Query(Constants.PLAYERS_BY_SEASON_PARAM) season: Int
    ): List<PlayerInfoResponseItemEntity>

    //Player Activity
    @GET(Constants.GET_PLAYERS)
    suspend fun getPlayersById(
        @Query(Constants.PLAYERS_BY_PLAYER_ID_PARAM) id: Int,
        @Query(Constants.PLAYERS_BY_SEASON_PARAM) season: Int
    ): Response<Players>

    @GET(Constants.GET_TRANSFERS)
    suspend fun getPlayersTransfers(
        @Query(Constants.TRANSFERS_BY_PLAYER_ID_PARAM) player: Int
    ): Response<Transfers>

    @GET(Constants.GET_SIDELINED)
    suspend fun getPlayersSidelined(
        @Query(Constants.SIDELINED_BY_PLAYER_ID_PARAM) player: Int
    ): Response<Sidelined>

    @GET(Constants.GET_TROPHIES)
    suspend fun getPlayersTrophies(
        @Query(Constants.TROPHIES_BY_PLAYER_ID_PARAM) player: Int
    ): Response<Trophies>

    @GET(Constants.GET_PLAYER_SQUADS)
    suspend fun getPlayersSquads(
        @Query(Constants.PLAYERS_SQUADS_BY_PLAYER_ID_PARAM) player: Int
    ): Response<Squads>

    @GET(Constants.GET_PLAYER_SEASONS)
    suspend fun getPlayersSeasons(
        @Query(Constants.PLAYERS_SEASONS_BY_PLAYER_ID_PARAM) player: Int
    ): Response<PlayerSeasons>

    //Team Activity
    @GET(Constants.GET_TEAMS_INFO)
    suspend fun getTeamInfoById(
        @Query(Constants.TEAMS_BY_ID_PARAM) id: Int
    ): Response<TeamsInfo>

    @GET(Constants.GET_FIXTURES)
    suspend fun getTeamMatches(
        @Query(Constants.FIXTURES_BY_TEAM_ID_PARAM) team: Int,
        @Query(Constants.FIXTURES_BY_SEASON_PARAM) season: Int
    ): Response<Fixtures>

    @GET(Constants.GET_TEAMS_SEASONS)
    suspend fun getTeamSeasons(
        @Query(Constants.TEAMS_SEASONS_BY_ID_PARAM) team: Int
    ): Response<TeamSeasons>

    @GET(Constants.GET_PLAYER_SQUADS)
    suspend fun getTeamPlayers(
        @Query(Constants.PLAYERS_SQUADS_BY_TEAM_ID_PARAM) team: Int
    ): Response<TeamSquadResponse>

    @GET(Constants.GET_TRANSFERS)
    suspend fun getTeamTransfers(
        @Query(Constants.TRANSFERS_BY_TEAM_ID_PARAM) team: Int
    ): Response<Transfers>
    //

    //Get All Leagues
    @GET(Constants.GET_LEAGUES)
    suspend fun getLeagues(
    ): Response<Leagues>


    //Tournament Activity
    @GET(Constants.GET_TOP_SCORERS)
    suspend fun getTopScorersByLeague(
        @Query(Constants.PLAYERS_TOP_SCORERS_BY_LEAGUE_ID_PARAM) league: Int,
        @Query(Constants.PLAYERS_TOP_SCORERS_BY_SEASON_PARAM) season: Int
    ): Response<TopScorers>

    @GET(Constants.GET_TOP_ASSISTS)
    suspend fun getTopAssistsByLeague(
        @Query(Constants.PLAYERS_TOP_ASSISTS_BY_LEAGUE_ID_PARAM) league: Int,
        @Query(Constants.PLAYERS_TOP_ASSISTS_BY_SEASON_PARAM) season: Int
    ): Response<TopAssists>

    @GET(Constants.GET_TOP_YELLOW_CARDS)
    suspend fun getTopYellowCardsByLeague(
        @Query(Constants.PLAYERS_TOP_YELLOW_CARDS_BY_LEAGUE_ID_PARAM) league: Int,
        @Query(Constants.PLAYERS_TOP_YELLOW_CARDS_BY_SEASON_PARAM) season: Int
    ): Response<TopYellowCards>

    @GET(Constants.GET_TOP_RED_CARDS)
    suspend fun getTopRedCardsByLeague(
        @Query(Constants.PLAYERS_TOP_RED_CARDS_BY_LEAGUE_ID_PARAM) league: Int,
        @Query(Constants.PLAYERS_TOP_RED_CARDS_BY_SEASON_PARAM) season: Int
    ): Response<TopRedCards>

    @GET(Constants.GET_TEAMS_INFO)
    suspend fun getTeamsById(
        @Query(Constants.TEAMS_BY_ID_PARAM) id: Int
    ): Response<TeamsInfo>
}