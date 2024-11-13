package com.elTohamy.flushy.presentation.model

import com.elTohamy.flushy.data.remote.dto.fixtures.today.TodayGamesResponseItem

data class LeagueWithMatches(
    val leagueId: Int,
    val matches: List<TodayGamesResponseItem?>
)