package com.elTohamy.flushy.data.state

import com.elTohamy.flushy.data.remote.dto.fixtures.Fixtures
import com.elTohamy.flushy.data.remote.dto.fixtures.byId.MatchByIdResponse
import com.elTohamy.flushy.data.remote.dto.fixtures.headToHead.Head2Head
import com.elTohamy.flushy.data.remote.dto.fixtures.lineups.Lineups
import com.elTohamy.flushy.data.remote.dto.fixtures.players.PlayersStatistics
import com.elTohamy.flushy.data.remote.dto.fixtures.today.TodayFixtures
import com.elTohamy.flushy.data.remote.dto.leagues.Leagues
import com.elTohamy.flushy.data.remote.dto.leagues.leagueById.LeaguesById
import com.elTohamy.flushy.data.remote.dto.news.FootballNews
import com.elTohamy.flushy.data.remote.dto.players.Players
import com.elTohamy.flushy.data.remote.dto.players.seasons.PlayerSeasons
import com.elTohamy.flushy.data.remote.dto.players.squads.Squads
import com.elTohamy.flushy.data.remote.dto.players.topAssists.TopAssists
import com.elTohamy.flushy.data.remote.dto.players.topRedCards.TopRedCards
import com.elTohamy.flushy.data.remote.dto.players.topScorers.TopScorers
import com.elTohamy.flushy.data.remote.dto.players.topYellowCards.TopYellowCards
import com.elTohamy.flushy.data.remote.dto.sidelined.Sidelined
import com.elTohamy.flushy.data.remote.dto.standings.StandingsItemItem
import com.elTohamy.flushy.data.remote.dto.teams.TeamsInfo
import com.elTohamy.flushy.data.remote.dto.teams.seasons.TeamSeasons
import com.elTohamy.flushy.data.remote.dto.teams.squads.TeamSquadResponse
import com.elTohamy.flushy.data.remote.dto.teams.stats.TeamStats
import com.elTohamy.flushy.data.remote.dto.teams.stats.TeamStatsResponse
import com.elTohamy.flushy.data.remote.dto.transfers.Transfers
import com.elTohamy.flushy.data.remote.dto.trophies.Trophies
import com.elTohamy.flushy.data.remote.dto.venues.Venues
import retrofit2.Response

sealed class MatchState {
    object Empty: MatchState()
    object Loading: MatchState()
    class Success(val data: Response<Fixtures>): MatchState()
    class Error(val message: String): MatchState()
}

sealed class TodayMatchesState {
    object Empty: TodayMatchesState()
    object Loading: TodayMatchesState()
    class Success(val data: Response<TodayFixtures>): TodayMatchesState()
    class Error(val message: String): TodayMatchesState()
}

sealed class LeagueStandingsState {
    object Empty: LeagueStandingsState()
    object Loading: LeagueStandingsState()
    class Success(val list: List<StandingsItemItem?>, val listOfList: List<List<StandingsItemItem?>?>?): LeagueStandingsState()
    class Error(val message: String): LeagueStandingsState()
}

//Tournament Activity
sealed class TournamentLeagueInfoState {
    data object Empty: TournamentLeagueInfoState()
    data object Loading: TournamentLeagueInfoState()
    class Success(val data: Response<LeaguesById>): TournamentLeagueInfoState()
    class Error(val message: String): TournamentLeagueInfoState()
}

sealed class MatchesByLeagueState {
    data object Empty: MatchesByLeagueState()
    data object Loading: MatchesByLeagueState()
    class Success(val data: Response<Fixtures>): MatchesByLeagueState()
    class Error(val message: String): MatchesByLeagueState()
}

sealed class TopScorersState {
    data object Empty: TopScorersState()
    data object Loading: TopScorersState()
    class Success(val data: Response<TopScorers>): TopScorersState()
    class Error(val message: String): TopScorersState()
}

sealed class TopAssistsState {
    data object Empty: TopAssistsState()
    data object Loading: TopAssistsState()
    class Success(val data: Response<TopAssists>): TopAssistsState()
    class Error(val message: String): TopAssistsState()
}

sealed class TopYellowCardsState {
    data object Empty: TopYellowCardsState()
    data object Loading: TopYellowCardsState()
    class Success(val data: Response<TopYellowCards>): TopYellowCardsState()
    class Error(val message: String): TopYellowCardsState()
}

sealed class TopRedCardsState {
    data object Empty: TopRedCardsState()
    data object Loading: TopRedCardsState()
    class Success(val data: Response<TopRedCards>): TopRedCardsState()
    class Error(val message: String): TopRedCardsState()
}

sealed class TournamentNewsState {
    data object Empty: TournamentNewsState()
    data object Loading: TournamentNewsState()
    class Success(val data: Response<FootballNews>): TournamentNewsState()
    class Error(val message: String): TournamentNewsState()
}
//



//Match Activity
sealed class MatchLeagueInfoState {
    object Empty: MatchLeagueInfoState()
    object Loading: MatchLeagueInfoState()
    class Success(val data: Response<LeaguesById>): MatchLeagueInfoState()
    class Error(val message: String): MatchLeagueInfoState()
}

sealed class MatchInfoState {
    object Empty: MatchInfoState()
    object Loading: MatchInfoState()
    class Success(val data: Response<MatchByIdResponse>): MatchInfoState()
    class Error(val message: String): MatchInfoState()
}

sealed class VenuesByIdState {
    object Empty: VenuesByIdState()
    object Loading: VenuesByIdState()
    class Success(val data: Response<Venues>): VenuesByIdState()
    class Error(val message: String): VenuesByIdState()
}

sealed class LineupsState {
    object Empty: LineupsState()
    object Loading: LineupsState()
    class Success(val data: Response<Lineups>): LineupsState()
    class Error(val message: String): LineupsState()
}

sealed class PlayersStatsByFixState {
    object Empty: PlayersStatsByFixState()
    object Loading: PlayersStatsByFixState()
    class Success(val data: Response<PlayersStatistics>): PlayersStatsByFixState()
    class Error(val message: String): PlayersStatsByFixState()
}
//

sealed class LeagueStatsState {
    object Empty: LeagueStatsState()
    object Loading: LeagueStatsState()
    class Success(val data: Response<TeamStats>, val teamData: TeamStatsResponse): LeagueStatsState()
    class Error(val message: String): LeagueStatsState()
}

sealed class HeadToHeadState {
    object Empty: HeadToHeadState()
    object Loading: HeadToHeadState()
    class Success(val data: Response<Head2Head>): HeadToHeadState()
    class Error(val message: String): HeadToHeadState()
}


//Player Activity
sealed class PlayerByIdState {
    object Empty: PlayerByIdState()
    object Loading: PlayerByIdState()
    class Success(val data: Response<Players>): PlayerByIdState()
    class Error(val message: String): PlayerByIdState()
}

sealed class PlayerTransfersState {
    object Empty: PlayerTransfersState()
    object Loading: PlayerTransfersState()
    class Success(val data: Response<Transfers>): PlayerTransfersState()
    class Error(val message: String): PlayerTransfersState()
}

sealed class PlayerSidelinedState {
    object Empty: PlayerSidelinedState()
    object Loading: PlayerSidelinedState()
    class Success(val data: Response<Sidelined>): PlayerSidelinedState()
    class Error(val message: String): PlayerSidelinedState()
}

sealed class PlayerTrophiesState {
    object Empty: PlayerTrophiesState()
    object Loading: PlayerTrophiesState()
    class Success(val data: Response<Trophies>): PlayerTrophiesState()
    class Error(val message: String): PlayerTrophiesState()
}

sealed class PlayerSquadsState {
    object Empty: PlayerSquadsState()
    object Loading: PlayerSquadsState()
    class Success(val data: Response<Squads>): PlayerSquadsState()
    class Error(val message: String): PlayerSquadsState()
}

sealed class PlayerSeasonsState {
    object Empty: PlayerSeasonsState()
    object Loading: PlayerSeasonsState()
    class Success(val data: Response<PlayerSeasons>): PlayerSeasonsState()
    class Error(val message: String): PlayerSeasonsState()
}
//Teams Activity
sealed class TeamInfoState {
    data object Empty: TeamInfoState()
    data object Loading: TeamInfoState()
    class Success(val data: Response<TeamsInfo>): TeamInfoState()
    class Error(val message: String): TeamInfoState()
}

sealed class TeamMatchesState {
    data object Empty: TeamMatchesState()
    data object Loading: TeamMatchesState()
    class Success(val data: Response<Fixtures>): TeamMatchesState()
    class Error(val message: String): TeamMatchesState()
}

sealed class TeamSeasonsState {
    data object Empty: TeamSeasonsState()
    data object Loading: TeamSeasonsState()
    class Success(val data: Response<TeamSeasons>): TeamSeasonsState()
    class Error(val message: String): TeamSeasonsState()
}

sealed class TeamPlayersState {
    data object Empty: TeamPlayersState()
    data object Loading: TeamPlayersState()
    class Success(val data: Response<TeamSquadResponse>): TeamPlayersState()
    class Error(val message: String): TeamPlayersState()
}

sealed class TeamTransfersState {
    data object Empty: TeamTransfersState()
    data object Loading: TeamTransfersState()
    class Success(val data: Response<Transfers>): TeamTransfersState()
    class Error(val message: String): TeamTransfersState()
}
sealed class TeamNewsState {
    data object Empty: TeamNewsState()
    data object Loading: TeamNewsState()
    class Success(val data: Response<FootballNews>): TeamNewsState()
    class Error(val message: String): TeamNewsState()
}
//

sealed class TeamByIdState {
    object Empty: TeamByIdState()
    object Loading: TeamByIdState()
    class Success(val data: Response<TeamsInfo>): TeamByIdState()
    class Error(val message: String): TeamByIdState()
}


sealed class LeaguesState {
    object Empty: LeaguesState()
    object Loading: LeaguesState()
    class Success(val data: Response<Leagues>): LeaguesState()
    class Error(val message: String): LeaguesState()
}

//Main
sealed class NewsState {
    object Empty: NewsState()
    object Loading: NewsState()
    class Success(val data: Response<FootballNews>): NewsState()
    class Error(val message: String): NewsState()
}

sealed class SavedNewsState {
    data object Added: SavedNewsState()
    data object Removed: SavedNewsState()
}