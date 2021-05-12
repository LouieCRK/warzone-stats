package com.louie.warzonestats.models.leaderboard.wins

data class Data(
    val count: Int,
    val id: Int,
    val leaderboardRangeType: Int,
    val leaderboardType: Int,
    val matchId: Any,
    val matchLeague: Any,
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