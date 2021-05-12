package com.louie.warzonestats.models.leaderboard.wins

data class LeaderboardWinsModel(
    val leaderboard: Leaderboard,
    val next: String,
    val playerRankings: List<Any>,
    val previous: String,
    val recordEndedAt: Int,
    val recordStartedAt: Int,
    val startedAt: Int
)