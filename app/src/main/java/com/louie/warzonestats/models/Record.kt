package com.louie.warzonestats.models

data class Record(
    val id: Int,
    val leaderboardRangeType: Int,
    val leaderboardType: Int,
    val matchId: Any,
    val matchLeague: Any,
    val playerKDLeague: Int,
    val position: Int,
    val positionGroupingKD: Int,
    val positionKD: Int,
    val positionMatchGroupingLeague: Int,
    val positionMatchLeague: Int,
    val recordId: String,
    val startedAt: String,
    val uno: String,
    val value: Int
)