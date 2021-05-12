package com.louie.warzonestats.models.leaderboard.kills

data class Data(
    val id: Int,
    val kills: Int,
    val leaderboardRangeType: Int,
    val leaderboardType: Int,
    val matchGroupingLeague: Int,
    val matchId: String,
    val matchLeague: Int,
    val playerKDGroupingLeague: Int,
    val playerKDLeague: Int,
    val position: Int,
    val positionGroupingKD: Int,
    val positionKD: Int,
    val positionMatchGroupingLeague: Int,
    val positionMatchLeague: Int,
    val recordId: Int,
    val startedAt: String,
    val uno: String,
    val user: User,
    val username: String,
    val value: Int
)