package com.elTohamy.flushy.presentation.activities.tournament.syncTab

class TodayGamesItem(
    val teamHName: String,
    val teamHLogo: String,
    val teamHResult: Any?,
    val time: String,
    val teamAResult: Any?,
    val teamALogo: String,
    val teamAName: String
)

class TopScorersItem(
    val playerRank: Int,
    val playerImage: String,
    val playerName: String,
    val apps: Int,
    val nonPenalty: Int,
    val goals: Int
)

class TopAssistItem(
    val playerRank: Int,
    val playerImage: String,
    val playerName: String,
    val apps: Int,
    val assists: Int
)

class TopTeamsPoints(
    val teamRank: Int,
    val teamLogo: String,
    val teamName: String,
    val apps: Int,
    val wins: Int,
    val draws: Int,
    val losses: Int,
    val points: Int
)

class TopTeamsClean(
    val teamRank: Int,
    val teamLogo: String,
    val teamName: String,
    val apps: Int,
    val cleanSheets: Int
)

class TopTeamsYellowCards(
    val teamRank: Int,
    val teamLogo: String,
    val teamName: String,
    val apps: Int,
    val yellowCards: Any
)

class TopTeamsRedCards(
    val teamRank: Int,
    val teamLogo: String,
    val teamName: String,
    val apps: Int,
    val redCards: Any
)

class TopTeamsPenalties(
    val teamRank: Int,
    val teamLogo: String,
    val teamName: String,
    val apps: Int,
    val missed: Int,
    val scored: Int,
    val total: Int
)

class TopTeamsLessConceded(
    val teamRank: Int,
    val teamLogo: String,
    val teamName: String,
    val apps: Int,
    val goalsConceded: Int
)

class TopTeamsMostScored(
    val teamRank: Int,
    val teamLogo: String,
    val teamName: String,
    val apps: Int,
    val goalsScored: Int
)