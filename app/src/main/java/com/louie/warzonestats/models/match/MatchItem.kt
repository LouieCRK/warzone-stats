package com.louie.warzonestats.models.match

data class MatchItem(
    val damage: Int,
    val deaths: Int,
    val gulagWin: Boolean,
    val id: String,
    val kills: Int,
    val matchStatData: MatchStatData,
    val matchTeamStat: MatchTeamStat,
    val mode: String,
    val position: Int,
    val refreshAt: Int,
    val startedAt: Int,
    val uno: String,
    val username: String
)