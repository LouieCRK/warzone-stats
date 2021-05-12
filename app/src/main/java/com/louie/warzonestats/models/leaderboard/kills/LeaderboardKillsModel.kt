package com.louie.warzonestats.models.leaderboard.kills

data class LeaderboardKillsModel(
    val leaderboard: Leaderboard,
    val next: String,
    val playerRankings: List<Any>,
    val previous: String,
    val recordEndedAt: Int,
    val recordStartedAt: Int,
    val startedAt: Int
)