package com.louie.warzonestats.models.match

data class MatchTeamStat(
    val matchId: String,
    val players: List<Player>
)